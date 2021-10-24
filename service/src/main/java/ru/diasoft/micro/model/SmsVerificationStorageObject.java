package ru.diasoft.micro.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
public class SmsVerificationStorageObject {
    private final String processGUID;
    private final String code;
    private final String phoneNumber;
}
