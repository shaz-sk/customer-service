package com.organization.customer.validator;

import com.organization.customer.data.TestData;
import com.organization.customer.dto.CustomerDto;
import com.organization.customer.models.Phone;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BindException;

import java.util.List;

import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class PhoneValidatorTest {
    @InjectMocks
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
        requestDTO = new CustomerDto("Joe","wood", List.of(phone));
        errors = new BindException(requestDTO, "CustomerDto");
        phoneValidator.validate(requestDTO, errors);
        Assertions.assertThat(errors.hasErrors()).isTrue();
    }

    @Test
    public void validateInvalidPhonenumber() {
        Phone phone1 = new Phone().phoneNumber("1234567890").active(false);
        requestDTO = new CustomerDto("Joe","wood", List.of(phone1));
        errors = new BindException(requestDTO, "CustomerDto");

        Phone phone2 = new Phone().phoneNumber("1234567891").active(false);
        doReturn(List.of(new CustomerDto("Joe", "wood", List.of(phone2)))).when(customerData).getCustomers();

        phoneValidator.validate(requestDTO, errors);
        Assertions.assertThat(errors.hasErrors()).isTrue();
    }

    @Test
    public void validateInvalidCustomer() {
        Phone phone1 = new Phone().phoneNumber("1234567890").active(false);
        requestDTO = new CustomerDto("Joe","wood", List.of(phone1));
        errors = new BindException(requestDTO, "CustomerDto");

        doReturn(List.of(new CustomerDto("Joe1", "wood", List.of(phone1)))).when(customerData).getCustomers();

        phoneValidator.validate(requestDTO, errors);
        Assertions.assertThat(errors.hasErrors()).isTrue();
    }

    @Test
    public void validatePhoneNumber() {
        Phone phone = new Phone().phoneNumber("1234567890").active(false);
        requestDTO = new CustomerDto("Joe","wood", List.of(phone));
        errors = new BindException(requestDTO, "CustomerDto");

        doReturn(List.of(requestDTO)).when(customerData).getCustomers();
        phoneValidator.validate(requestDTO, errors);
        Assertions.assertThat(errors.hasErrors()).isFalse();
    }
}
