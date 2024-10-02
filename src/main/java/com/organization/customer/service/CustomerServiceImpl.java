package com.organization.customer.service;

import com.organization.customer.data.TestData;
import com.organization.customer.dto.CustomerDto;
import com.organization.customer.mapper.ContactDetailsMapper;
import com.organization.customer.mapper.CustomerMapper;
import com.organization.customer.models.ContactDetails;
import com.organization.customer.models.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class CustomerServiceImpl implements CustomerService {
    private final CustomerMapper customerMapper;
    private final ContactDetailsMapper contactDetailsMapper;
    private final TestData customerData;

    public CustomerServiceImpl(CustomerMapper customerMapper, ContactDetailsMapper contactDetailsMapper, TestData customerData) {
        this.customerMapper = customerMapper;
        this.contactDetailsMapper = contactDetailsMapper;
        this.customerData = customerData;
    }

    @Override
    public ContactDetails getContactDetails() {
        return contactDetailsMapper.mapDtoToResponse(customerData);
    }

    @Override
    public Customer getCustomerDetails(String firstName) {
        Optional<CustomerDto> customer = customerData.getCustomers().stream()
            .filter( c -> c.getFirstName().equals(firstName)).findFirst();
        if(customer.isPresent()) {
            return customerMapper.mapCustomer(customerData.getCustomers().get(0));
        }
        log.debug("Customer details not found. ");

        return new Customer();
    }
}
