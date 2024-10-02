package com.organization.customer.delegate;

import com.organization.customer.api.ContactDetailsApiDelegate;
import com.organization.customer.dto.CustomerDto;
import com.organization.customer.exception.InvalidRequestException;
import com.organization.customer.models.ContactDetails;
import com.organization.customer.models.Customer;
import com.organization.customer.models.Phone;
import com.organization.customer.service.CustomerService;
import com.organization.customer.service.PhoneService;
import com.organization.customer.validator.AccessValidator;
import com.organization.customer.validator.PhoneValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@Component
@Slf4j
public class ContactDetailsApiDelegateImpl implements ContactDetailsApiDelegate {

    private final AccessValidator accessValidator;

    private final PhoneValidator phoneValidator;

    private final PhoneService phoneService;

    private final CustomerService customerService;

    public ContactDetailsApiDelegateImpl(AccessValidator accessValidator, PhoneValidator phoneValidator,
                                         PhoneService phoneService, CustomerService customerService) {
        this.accessValidator = accessValidator;
        this.phoneValidator = phoneValidator;
        this.phoneService = phoneService;
        this.customerService = customerService;
    }

    @Override
    public ResponseEntity<ContactDetails> getContactDetails() {
        validate(accessValidator, "");
        return new ResponseEntity<>(customerService.getContactDetails(), OK);
    }

    @Override
    public ResponseEntity<Customer> getCustomerDetails(String firstName) {
        validate(accessValidator, firstName);
        return new ResponseEntity(customerService.getCustomerDetails(firstName), OK);
    }

    @Override
    public ResponseEntity<Phone> updatePhoneStatus(String firstName, Phone phoneNumber) {
        CustomerDto customerDto = new CustomerDto(firstName, "", List.of(phoneNumber));
        validate(phoneValidator, customerDto);
        return new ResponseEntity(phoneService.saveStatus(phoneNumber, firstName), OK);
    }

    private void validate(final org.springframework.validation.Validator validator, final Object toValidate) {

        final DataBinder binder = new DataBinder(toValidate);
        binder.setValidator(validator);
        binder.validate();

        final BindingResult bindingResult = binder.getBindingResult();
        if (bindingResult.hasErrors()) {
            throw new InvalidRequestException(bindingResult);
        }
    }
}
