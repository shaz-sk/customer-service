package com.belong.customer.validator;

import com.belong.customer.dto.CustomerDto;
import com.belong.customer.data.TestData;
import com.belong.customer.models.Phone;
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
                    .filter(p -> p.getPhoneNumber().equals(phoneNumber)).findFirst();
            if(!phone.isPresent()) {
                errors.reject("102", "Phone number does not belong to the user");
                return;
            }
        } else {
            errors.reject("102", "Phone number does not belong to the user");
            return;
        }


    }
}
