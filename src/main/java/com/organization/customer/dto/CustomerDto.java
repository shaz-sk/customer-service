package com.organization.customer.dto;

import com.organization.customer.models.Phone;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class CustomerDto {

    private final String firstName;
    private final String lastName;
    private final List<Phone> phoneNumbers;

    public CustomerDto(String firstName, String lastName, List<Phone> phoneNumbers) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumbers = phoneNumbers;
    }
}
