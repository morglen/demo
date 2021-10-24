package ru.diasoft.micro.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ru.diasoft.micro.domain.SmsVerificationCheckRequest;
import ru.diasoft.micro.domain.SmsVerificationCheckResponse;
import ru.diasoft.micro.domain.SmsVerificationPostRequest;
import ru.diasoft.micro.domain.SmsVerificationPostResponse;
import ru.diasoft.micro.lib.config.aop.Loggable;
import ru.diasoft.micro.service.guid.GUIDService;

@Service
@Primary
@Loggable
@RequiredArgsConstructor
public class SmsVerificationServiceImpl implements SmsVerificationService {
    private final GUIDService guidService;

    @Override
    public SmsVerificationCheckResponse dsSmsVerificationCheck(SmsVerificationCheckRequest smsVerificationCheckRequest) {
        boolean isSuccess = guidService.checkCode(smsVerificationCheckRequest.getProcessGUID(), smsVerificationCheckRequest.getCode());
        return new SmsVerificationCheckResponse(isSuccess);
    }

    @Override
    public SmsVerificationPostResponse dsSmsVerificationCreate(SmsVerificationPostRequest smsVerificationPostRequest) {
        String uid = guidService.getGUID(smsVerificationPostRequest.getPhoneNumber());
        return new SmsVerificationPostResponse(uid);
    }
}
