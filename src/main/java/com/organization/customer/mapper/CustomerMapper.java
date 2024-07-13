package com.organization.customer.mapper;

import com.organization.customer.dto.CustomerDto;
import com.organization.customer.models.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    Customer mapCustomer (CustomerDto customer);

}
