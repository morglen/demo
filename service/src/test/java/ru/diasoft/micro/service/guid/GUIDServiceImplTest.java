package ru.diasoft.micro.service.guid;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import ru.diasoft.micro.model.SmsVerificationStorageObject;
import ru.diasoft.micro.service.storage.GUIDStorageService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;

public class GUIDServiceImplTest {

    private GUIDServiceImpl guidService;
    SmsVerificationStorageObject smsVerificationStorageObject;

    @Before
    public void init() {
        GUIDStorageService service = Mockito.mock(GUIDStorageService.class);
        Mockito.doAnswer((Answer<Void>) invocation -> {
                    smsVerificationStorageObject = invocation.getArgument(0);
                    return null;
                }
        ).when(service).save(any());

        Mockito.doAnswer((Answer<Void>) invocation -> {
                    smsVerificationStorageObject = null;
                    return null;
                }
        ).when(service).delete(any());
        Mockito.doAnswer((Answer<SmsVerificationStorageObject>) invocation -> smsVerificationStorageObject)
                .when(service).getSmsVerificationStorageObject(any());
        guidService = new GUIDServiceImpl(service);
    }

    @Test
    public void getGUID() {
        String guid = guidService.getGUID("000000");
        assertEquals(guid, smsVerificationStorageObject.getProcessGUID());
    }

    @Test
    public void checkCode() {
        String guid = guidService.getGUID("000000");
        String code = smsVerificationStorageObject.getCode();
        assertTrue(guidService.checkCode(guid, code));
        assertFalse(guidService.checkCode(guid, code));
    }
}