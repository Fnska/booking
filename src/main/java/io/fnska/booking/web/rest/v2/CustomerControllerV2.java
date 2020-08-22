package io.fnska.booking.web.rest.v2;

import io.fnska.booking.converter.Converter;
import io.fnska.booking.converter.dto.CustomerDto;
import io.fnska.booking.domain.Customer;
import io.fnska.booking.domain.Reservation;
import io.fnska.booking.repository.CustomerRepository;
import io.fnska.booking.repository.ReservationRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v2")
@AllArgsConstructor
public class CustomerControllerV2 {
    private CustomerRepository customerRepository;
    private Converter<Customer, CustomerDto> converter;
    private ReservationRepository reservationRepository;

    @GetMapping("/customers")
    public ResponseEntity<List<CustomerDto>> getCustomers(@PageableDefault(sort = {"name"}) Pageable pageable) {
        Page<Customer> page = customerRepository.findAll(pageable);
        Page<CustomerDto> pageDto = converter.entityToDto(page);
        return new ResponseEntity<>(pageDto.getContent(), HttpStatus.OK);
    }

    @GetMapping("/customers/search")
    public ResponseEntity<List<CustomerDto>> getCustomersByDate(
            @RequestParam(name = "date") Date date,
            @PageableDefault(sort = {"name"}) Pageable pageable
    ) {
        Page<Customer> page = customerRepository.findCustomersByReservations_ReservationDate(date, pageable);
        Page<CustomerDto> pageDto = converter.entityToDto(page);
        return new ResponseEntity<>(pageDto.getContent(), HttpStatus.OK);
    }

    @PostMapping("/customers")
    public ResponseEntity<CustomerDto> saveCustomer(@RequestBody CustomerDto dto) {
        Customer customer = converter.dtoToEntity(dto);
        if (customerRepository.existsByEmail(customer.getEmail())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        customer = customerRepository.save(customer);
        CustomerDto dtoFromDb = converter.entityToDto(customer);
        return new ResponseEntity<>(dtoFromDb, HttpStatus.CREATED);
    }

    @PostMapping("/customers/addOnDate")
    public ResponseEntity<CustomerDto> addCustomerByDate(
            @RequestParam(name = "date") Date date,
            @RequestBody CustomerDto dto
    ) {
        Customer customer = converter.dtoToEntity(dto);
        Optional<Customer> opt = customerRepository.findOneByEmail(customer.getEmail());
        if (!opt.isPresent()) {
            customer = customerRepository.save(customer);
            Reservation reservation = reservationRepository.findOneByReservationDate(date);
            customer.addReservation(reservation);
            customer = customerRepository.save(customer);
            CustomerDto dtoFromDb = converter.entityToDto(customer);
            return new ResponseEntity<>(dtoFromDb, HttpStatus.CREATED);
        }

        customer = opt.get();
        Reservation reservation = reservationRepository.findOneByReservationDate(date);
        customer.addReservation(reservation);
        customer = customerRepository.save(customer);
        CustomerDto dtoFromDb = converter.entityToDto(customer);
        return new ResponseEntity<>(dtoFromDb, HttpStatus.CREATED);
    }
}
