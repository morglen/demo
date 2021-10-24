package ru.diasoft.micro.service.storage;

import org.springframework.stereotype.Service;
import ru.diasoft.micro.lib.config.aop.Loggable;
import ru.diasoft.micro.model.SmsVerificationStorageObject;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Loggable
public class GUIDStorageServiceImpl implements GUIDStorageService {

    private static final Map<String, SmsVerificationStorageObject> storage = new ConcurrentHashMap<>();

    @Override
    public SmsVerificationStorageObject getSmsVerificationStorageObject(String processGUID) {
        return storage.get(processGUID);
    }

    @Override
    public void save(SmsVerificationStorageObject object) {
        storage.put(object.getProcessGUID(), object);
    }

    @Override
    public void delete(String processGUID) {
        storage.remove(processGUID);
    }
}
