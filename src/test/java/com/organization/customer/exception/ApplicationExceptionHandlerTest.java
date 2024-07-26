package com.organization.customer.exception;

import com.organization.customer.models.Error;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.converter.HttpMessageNotReadableException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ApplicationExceptionHandlerTest {

    @InjectMocks
    private ApplicationExceptionHandler applicationExceptionHandler;

    @Mock
    private HttpMessageNotReadableException hex;


    @Test
    void handleOtherException() {
        List<Error> response = applicationExceptionHandler.handleValidationError(hex);
        assertEquals("400", response.get(0).getCode());
        assertEquals("Invalid field format", response.get(0).getMessage());
    }
}