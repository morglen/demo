package ru.diasoft.micro.service.storage;

import ru.diasoft.micro.model.SmsVerificationStorageObject;

public interface GUIDStorageService {

    SmsVerificationStorageObject getSmsVerificationStorageObject(String processGUID);

    void save(SmsVerificationStorageObject object);

    void delete(String processGUID);
}
