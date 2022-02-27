package com.belong.customer.validator;

import com.belong.customer.data.TestData;
import com.belong.customer.dto.CustomerDto;
import com.belong.customer.models.Phone;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BindException;

import java.util.Arrays;

import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class PhoneValidatorTest {
    @InjectMocks
    @Spy
    private PhoneValidator phoneValidator;

    @Spy
    private TestData customerData;

    private BindException errors;

    private CustomerDto requestDTO;


    @Test
    public void supports() {
        final boolean result = phoneValidator.supports(CustomerDto.class);
        Assertions.assertThat(result).isTrue();
    }

    @Test
    public void validatePhonenumberLength() {
        Phone phone = new Phone().phoneNumber("123456789").active(false);
        requestDTO = new CustomerDto("Joe","wood", Arrays.asList(phone));
        errors = new BindException(requestDTO, "CustomerDto");
        phoneValidator.validate(requestDTO, errors);
        Assertions.assertThat(errors.hasErrors()).isTrue();
    }

    @Test
    public void validateInvalidPhonenumber() {
        Phone phone1 = new Phone().phoneNumber("1234567890").active(false);
        requestDTO = new CustomerDto("Joe","wood", Arrays.asList(phone1));
        errors = new BindException(requestDTO, "CustomerDto");

        Phone phone2 = new Phone().phoneNumber("1234567891").active(false);
        doReturn(Arrays.asList(new CustomerDto("Joe","wood", Arrays.asList(phone2)))).when(customerData).getCustomers();

        phoneValidator.validate(requestDTO, errors);
        Assertions.assertThat(errors.hasErrors()).isTrue();
    }

    @Test
    public void validateInvalidCustomer() {
        Phone phone1 = new Phone().phoneNumber("1234567890").active(false);
        requestDTO = new CustomerDto("Joe","wood", Arrays.asList(phone1));
        errors = new BindException(requestDTO, "CustomerDto");

        doReturn(Arrays.asList(new CustomerDto("Joe1","wood", Arrays.asList(phone1)))).when(customerData).getCustomers();

        phoneValidator.validate(requestDTO, errors);
        Assertions.assertThat(errors.hasErrors()).isTrue();
    }

    @Test
    public void validatePhonenumber() {
        Phone phone = new Phone().phoneNumber("1234567890").active(false);
        requestDTO = new CustomerDto("Joe","wood", Arrays.asList(phone));
        errors = new BindException(requestDTO, "CustomerDto");

        doReturn(Arrays.asList(requestDTO)).when(customerData).getCustomers();
        phoneValidator.validate(requestDTO, errors);
        Assertions.assertThat(errors.hasErrors()).isFalse();
    }
}
