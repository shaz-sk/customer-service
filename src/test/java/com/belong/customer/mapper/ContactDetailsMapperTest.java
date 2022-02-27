package com.belong.customer.mapper;

import com.belong.customer.data.TestData;
import com.belong.customer.dto.CustomerDto;
import com.belong.customer.models.ContactDetails;
import com.belong.customer.models.Phone;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class ContactDetailsMapperTest {

    @InjectMocks
    @Spy
    private ContactDetailsMapperImpl mapper;

    @Spy
    private TestData customerData;

    @Spy
    private CustomerMapper customerMapper;


    @Test
    public void mapDtoToResponse() {
        Phone phone = new Phone().phoneNumber("0400000000").active(true);

        doReturn(Arrays.asList( new CustomerDto("Joe","wood", Arrays.asList(phone))))
                .when(customerData).getCustomers();

        final ContactDetails response = mapper.mapDtoToResponse(customerData);
        assertEquals(1,response.getCustomers().size());
    }

}
