package com.organization.customer.data;

import com.organization.customer.dto.CustomerDto;
import com.organization.customer.models.Phone;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@Data
@Getter
@Setter
public class TestData {

    private final List<CustomerDto> customers = new ArrayList<>();

    public TestData() {

        Phone phone1 = new Phone().phoneNumber("0400000000").active(true);
        Phone phone2 = new Phone().phoneNumber("0400000002").active(false);
        Phone phone3 = new Phone().phoneNumber("0400000003").active(false);
        Phone phone4 = new Phone().phoneNumber("0400000004").active(true);

        CustomerDto customer1 = new CustomerDto("Joe1", "Smith", List.of(phone1, phone2));
        CustomerDto customer2 = new CustomerDto("Joe2", "Smith", List.of(phone3, phone4));
        customers.add(customer1);
        customers.add(customer2);
    }

    public List<CustomerDto> getCustomers(){
        return this.customers;
    }
}
