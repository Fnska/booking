package io.fnska.booking.web.rest.v2;

import io.fnska.booking.converter.Converter;
import io.fnska.booking.converter.dto.CustomerDto;
import io.fnska.booking.domain.Customer;
import io.fnska.booking.repository.CustomerRepository;
import io.fnska.booking.repository.ReservationRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("integration")
public class CustomerControllerV2IntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private Converter<Customer, CustomerDto> converter;

    @Autowired
    private ReservationRepository reservationRepository;


    @Test
    public void whenGetRequestToCustomers_thenCorrectResponse() throws Exception {
        mockMvc.perform(get("/api/v2/customers")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void whenGetRequestToCustomersSearch_thenCorrectResponse() throws Exception {
        mockMvc.perform(get("/api/v2/customers/search?date=2020-01-01")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void whenPostRequestToCustomersAndValidCustomer_thenCorrectResponse() throws Exception {
        String body = "{\"name\": \"bob\", \"surname\": \"dod\", \"email\" : \"bob@dod.com\"}";
        mockMvc.perform(post("/api/v2/customers")
                .content(body)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", is("bob")))
                .andExpect(jsonPath("$.surname", is("dod")))
                .andExpect(jsonPath("$.email", is("bob@dod.com")));
    }

    @Test
    public void whenPostRequestToCustomersAndDuplicatedCustomer_thenCorrectResponse() throws Exception {
        String body = "{\"name\": \"Ivan\", \"surname\": \"Ivan\", \"email\" : \"ivan@ivan.com\"}";
        mockMvc.perform(post("/api/v2/customers")
                .content(body)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void whenPostRequestToCustomersAddOnDate_thenCorrectResponse() throws Exception {
        String body = "{\"name\": \"Petr\", \"surname\": \"Petrov\", \"email\" : \"petr@petr.com\"}";
        mockMvc.perform(post("/api/v2/customers/addOnDate?date=2020-01-01")
                .content(body)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", is("Petr")))
                .andExpect(jsonPath("$.surname", is("Petrov")))
                .andExpect(jsonPath("$.email", is("petr@petr.com")));
    }

    @Test
    public void whenPutRequestToCustomerDeleteFromDate_thenCorrectResponse() throws Exception {
        String body = "{\"name\": \"Petr\", \"surname\": \"Petrov\", \"email\" : \"petr@petr.com\"}";
        mockMvc.perform(put("/api/v2/customers/deleteFromDate?date=2020-05-05")
                .content(body)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void whenPutRequestToCustomerDeleteFromDateInValidCustomer_thenCorrectResponse() throws Exception {
        String body = "{\"name\": \"qwe\", \"surname\": \"qwe\", \"email\" : \"qwe@qwe.com\"}";
        mockMvc.perform(put("/api/v2/customers/deleteFromDate?date=2020-01-01")
                .content(body)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}