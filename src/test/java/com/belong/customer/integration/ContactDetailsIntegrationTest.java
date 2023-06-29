package com.belong.customer.integration;

import com.belong.customer.CustomerApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc(addFilters = false)
@ContextConfiguration(classes=CustomerApplication.class)
public class ContactDetailsIntegrationTest {
    @Autowired
    private MockMvc mvc;

    @LocalServerPort
    private int randomServerPort = 0;

    @Test
    public void updateCustomer () throws Exception {

        mvc.perform(put("http://localhost:"+randomServerPort+"/api/v1/customers/Joe1/contactDetail")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\n" +
                    "            \"phoneNumber\" : \"0400000000\",\n" +
                    "            \"active\" : true\n" +
                    "       }"
            )).andExpect (status().isOk()).
                andExpect(content().string("{\"phoneNumber\":\"0400000000\",\"active\":true}"));
    }
}

