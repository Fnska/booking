package io.fnska.booking.web.rest.v1;

import io.fnska.booking.repository.projections.CustomerProjection;
import io.fnska.booking.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class CustomerControllerV1 {
    private CustomerRepository customerRepository;

    @GetMapping("/customers")
    public ResponseEntity<List<CustomerProjection>> getCustomers(@PageableDefault(sort = {"name"}) Pageable pageable) {
        Page<CustomerProjection> page = customerRepository.findBy(pageable);
        return new ResponseEntity<>(page.getContent(), HttpStatus.OK);
    }

    @GetMapping("/customers/search")
    public ResponseEntity<List<CustomerProjection>> getCustomersByDate(
            @RequestParam(name = "date") Date date,
            @PageableDefault(sort = {"name"}) Pageable pageable) {
        Page<CustomerProjection> page = customerRepository.findCustomerProjectionsByReservations_ReservationDate(date, pageable);
        return new ResponseEntity<>(page.getContent(), HttpStatus.OK);
    }

}
