package com.organization.customer.delegate;

import com.organization.customer.models.ContactDetails;
import com.organization.customer.models.Customer;
import com.organization.customer.service.CustomerService;
import com.organization.customer.validator.AccessValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.OK;

@ExtendWith(MockitoExtension.class)
class ContactDetailsApiDelegateImplTest {
    @InjectMocks
    private ContactDetailsApiDelegateImpl contactDetailsApiDelegate;
    @Mock
    private ContactDetails  contactDetails;
    @Mock
    private AccessValidator accessValidator;
    @Mock
    private Customer customer;
    @Mock
    private CustomerService customerService;

    @BeforeEach
    void setup() {
        when(accessValidator.supports(any())).thenReturn(true);
    }

    @Test
    void shouldGetContactDetails() {
        when(customerService.getContactDetails()).thenReturn(contactDetails);

        ResponseEntity<ContactDetails> responseEntity = contactDetailsApiDelegate.getContactDetails();
        verify(accessValidator).validate(anyString(),any(Errors.class ));
        verify(customerService).getContactDetails();
        assertThat(responseEntity.getStatusCode()).isEqualTo(OK);
        assertThat(responseEntity.getBody()).isEqualTo(contactDetails);
    }

    @Test
    void shouldGetCustomerDetails() {
        String firstName = "firstName";

        when(customerService.getCustomerDetails(anyString())).thenReturn(customer);

        ResponseEntity<Customer> responseEntity = contactDetailsApiDelegate.getCustomerDetails(firstName);
        verify(accessValidator).validate(anyString(),any(Errors.class ));
        verify(customerService).getCustomerDetails(firstName);
        assertThat(responseEntity.getStatusCode()).isEqualTo(OK);
        assertThat(responseEntity.getBody()).isEqualTo(customer);
    }

}