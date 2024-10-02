package com.organization.customer.service;

import com.organization.customer.models.ContactDetails;
import com.organization.customer.models.Customer;

public interface CustomerService {

    ContactDetails getContactDetails();

    Customer getCustomerDetails(String firstName);

}
