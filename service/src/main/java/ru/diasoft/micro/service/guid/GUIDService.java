package ru.diasoft.micro.service.guid;

public interface GUIDService {

    String getGUID(String phoneNumber);

    boolean checkCode(String processGUID, String code);
}
