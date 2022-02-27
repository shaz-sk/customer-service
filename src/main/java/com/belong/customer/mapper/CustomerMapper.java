package com.belong.customer.mapper;

import com.belong.customer.dto.CustomerDto;
import com.belong.customer.models.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    Customer mapCustomer (CustomerDto customer);

}
