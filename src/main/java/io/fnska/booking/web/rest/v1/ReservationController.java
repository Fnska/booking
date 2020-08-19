package io.fnska.booking.web.rest.v1;

import io.fnska.booking.domain.CustomerReservation;
import io.fnska.booking.domain.Reservation;
import io.fnska.booking.domain.dto.CustomerReservationDTO;
import io.fnska.booking.service.CustomerReservationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class ReservationController {
    private CustomerReservationService customerReservationService;

    @GetMapping("/reservations")
    public ResponseEntity<List<Reservation>> getAllReservedDays() {
        List<Reservation> reservations = customerReservationService.findAll();
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    @PostMapping("/reservations")
    public ResponseEntity<CustomerReservation> saveCustomerReservation(@RequestBody CustomerReservationDTO customerReservationDTO) {
        CustomerReservation customerReservation = customerReservationService.saveCustomerReservation(customerReservationDTO);
        return new ResponseEntity<>(customerReservation, HttpStatus.CREATED);
    }
}
