package io.fnska.booking.web.rest.v1;

import com.fasterxml.jackson.annotation.JsonView;
import io.fnska.booking.domain.Customer;
import io.fnska.booking.repository.CustomerRepository;
import io.fnska.booking.domain.view.Views;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class CustomerController {
    private CustomerRepository customerRepository;

    @GetMapping("/customers")
    @JsonView(Views.CustomerView.class)
    public ResponseEntity<List<Customer>> getAllCustomers(@RequestParam(required = false) String date) {
        List<Customer> customers = customerRepository.findAll();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }
}
