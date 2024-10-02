package com.organization.customer.service;

import com.organization.customer.dto.CustomerDto;
import com.organization.customer.data.TestData;
import com.organization.customer.models.Phone;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class PhoneServiceImpl implements PhoneService{

    @Autowired
    private TestData customerData;

    @Override
    public Phone saveStatus(Phone phoneNumber, String firstName)  {

        Optional<CustomerDto> customer =
                customerData.getCustomers().stream().filter( c -> c.getFirstName().equals(firstName)).findFirst();
        if(customer.isPresent()) {
            Optional<Phone> phone = customer.get().getPhoneNumbers().stream()
                .filter( p -> p.getPhoneNumber().equals(phoneNumber.getPhoneNumber())).findFirst();
            if(phone.isPresent()) {
                phone.get().setActive(phoneNumber.getActive());
                return phone.get();
            }
        }
        log.debug("Unable to update phone details. ");
        return new Phone();
    }


}
