package ru.diasoft.micro.service.guid;

import com.mysema.commons.lang.Assert;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import ru.diasoft.micro.lib.config.aop.Loggable;
import ru.diasoft.micro.model.SmsVerificationStorageObject;
import ru.diasoft.micro.service.storage.GUIDStorageService;

import java.util.Random;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Loggable
public class GUIDServiceImpl implements GUIDService {

    private final Logger logger = LogManager.getLogger(GUIDServiceImpl.class);
    private final GUIDStorageService guidStorageService;

    @Override
    public String getGUID(String phoneNumber) {
        Assert.notNull(phoneNumber, "Phone number can't be null");

        String guid = UUID.randomUUID().toString();
        String code = String.format("%05d", new Random().nextInt(100000));
        SmsVerificationStorageObject storageObject = new SmsVerificationStorageObject(guid, code, phoneNumber);
        guidStorageService.save(storageObject);
        logger.info("SMS verification storage object created = {}", storageObject);
        return guid;
    }

    @Override
    public boolean checkCode(String processGUID, String code) {
        Assert.notNull(processGUID, "ProcessGUID can't be null");
        Assert.notNull(code, "Code can't be null");
        SmsVerificationStorageObject object = guidStorageService.getSmsVerificationStorageObject(processGUID);
        if (object == null) {
            return false;
        }
        if (object.getCode().equals(code)) {
            guidStorageService.delete(processGUID);
            return true;
        }
        return false;
    }
}
