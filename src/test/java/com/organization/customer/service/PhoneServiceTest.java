package com.organization.customer.service;

import com.organization.customer.data.TestData;
import com.organization.customer.dto.CustomerDto;
import com.organization.customer.models.Phone;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class PhoneServiceTest {
    @InjectMocks
    @Spy
    private PhoneServiceImpl phoneService;

    @Spy
    private TestData customerData;

    @Test
    void saveStatusSuccess() {
        Phone phone = new Phone().phoneNumber("0400000000").active(true);

        doReturn(Arrays.asList( new CustomerDto("Joe","wood", Arrays.asList(phone))))
                .when(customerData).getCustomers();
        Phone response = phoneService.saveStatus(phone, "Joe");
        assertEquals(phone, response);
    }

    @Test
    void saveStatusUnsuccessful() {
        Phone phone = new Phone().phoneNumber("0400000000").active(true);

        doReturn(Arrays.asList( new CustomerDto("Joe","wood", Arrays.asList(phone))))
                .when(customerData).getCustomers();
        Phone response = phoneService.saveStatus(phone, "Joe1");
        assertNotEquals(phone, response);
    }


}
