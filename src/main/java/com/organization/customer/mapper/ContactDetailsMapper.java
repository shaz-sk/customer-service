package com.organization.customer.mapper;

import com.organization.customer.data.TestData;
import com.organization.customer.models.ContactDetails;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses ={CustomerMapper.class})
public interface ContactDetailsMapper {

    ContactDetails mapDtoToResponse(TestData contactDetails);

}
