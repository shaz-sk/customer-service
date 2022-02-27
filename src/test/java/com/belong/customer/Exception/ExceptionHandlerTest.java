package com.belong.customer.Exception;

import com.belong.customer.models.Error;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.converter.HttpMessageNotReadableException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ExceptionHandlerTest {

    @InjectMocks
    @Spy
    private ExceptionHandler exceptionHandler;

    @Mock
    private HttpMessageNotReadableException hex;


    @Test
    void handleOtherException() {
        List<Error> response = exceptionHandler.handleValidationError(hex);
        assertEquals("400", response.get(0).getCode());
        assertEquals("Invalid field format", response.get(0).getMessage());
    }
}