package com.organization.customer.validator;

import com.organization.customer.dto.CustomerDto;
import com.organization.customer.data.TestData;
import com.organization.customer.models.Phone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import java.util.Optional;


@Component
public class PhoneValidator implements org.springframework.validation.Validator {

    @Autowired
    private TestData customerData;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(CustomerDto.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CustomerDto customerDto = (CustomerDto) target;
        String user = customerDto.getFirstName();
        String phoneNumber = customerDto.getPhoneNumbers().get(0).getPhoneNumber();

        if (phoneNumber.toCharArray().length < 10) {
            errors.reject("101", "Invalid Phone number");
            return;
        }

        Optional<CustomerDto> customer =
                customerData.getCustomers().stream().filter( c -> c.getFirstName().equals(user)).findFirst();

        if(customer.isPresent()) {
            Optional<Phone> phone = customer.get().getPhoneNumbers().stream()
                    .filter(phoneRecord -> phoneRecord.getPhoneNumber().equals(phoneNumber)).findFirst();
            if(phone.isEmpty()) {
                errors.reject("102", "Unable to activate");
            }
        } else {
            errors.reject("102", "Unable to activate");
        }


    }
}
