package com.belong.customer.mapper;

import com.belong.customer.data.TestData;
import com.belong.customer.models.ContactDetails;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses ={CustomerMapper.class})
public interface ContactDetailsMapper {

    ContactDetails mapDtoToResponse(TestData contactDetails);

}
