package com.organization.customer.delegate;

import com.organization.customer.exception.InvalidRequestException;
import com.organization.customer.api.ContactDetailsApiDelegate;
import com.organization.customer.dto.CustomerDto;
import com.organization.customer.data.TestData;
import com.organization.customer.mapper.CustomerMapper;
import com.organization.customer.mapper.ContactDetailsMapper;
import com.organization.customer.models.ContactDetails;
import com.organization.customer.models.Customer;
import com.organization.customer.models.Phone;
import com.organization.customer.service.PhoneService;
import com.organization.customer.validator.AccessValidator;
import com.organization.customer.validator.PhoneValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;

import java.util.Arrays;
import java.util.Optional;

@Component
@Slf4j
public class ContactDetailsApiDelegateImpl implements ContactDetailsApiDelegate {

    @Autowired
    private ContactDetailsMapper contactDetailsMapper;

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private TestData customerData;

    @Autowired
    private AccessValidator accessValidator;

    @Autowired
    private PhoneValidator phoneValidator;

    @Autowired
    private PhoneService phoneService;

    @Override
    public ResponseEntity<ContactDetails> getContactDetails() {

        validate(accessValidator, "");
        return new ResponseEntity<>(contactDetailsMapper.mapDtoToResponse(customerData), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Customer> getCustomerDetails(String firstName) {

        validate(accessValidator, firstName);
        Optional<CustomerDto> customer =
                customerData.getCustomers().stream().filter( c -> c.getFirstName().equals(firstName)).findFirst();
        if(customer.isPresent()) {
            return new ResponseEntity(customerMapper.mapCustomer(customerData.getCustomers().get(0)), HttpStatus.OK);
        }
        log.debug("Customer details not found. ");

        return new ResponseEntity(new Customer(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Phone> updatePhoneStatus(String firstName, Phone phoneNumber) {
        CustomerDto customerDto = new CustomerDto(firstName, "", Arrays.asList(phoneNumber));
        validate(phoneValidator, customerDto);
        return new ResponseEntity(phoneService.saveStatus(phoneNumber, firstName), HttpStatus.OK);
    }


    public void validate(final org.springframework.validation.Validator validator, final Object toValidate) {

        final DataBinder binder = newBinderInstance(toValidate);
        binder.setValidator(validator);
        binder.validate();

        final BindingResult bindingResult = binder.getBindingResult();
        if (bindingResult.hasErrors()) {
            throw new InvalidRequestException(bindingResult);
        }
    }

    protected DataBinder newBinderInstance(final Object toValidate) {

        final DataBinder binder = new DataBinder(toValidate);
        return binder;
    }
}
