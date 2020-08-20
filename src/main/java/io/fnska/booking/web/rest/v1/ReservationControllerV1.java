package io.fnska.booking.web.rest.v1;

import io.fnska.booking.repository.projections.ReservationProjection;
import io.fnska.booking.repository.ReservationRepository;
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

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class ReservationControllerV1 {
    private ReservationRepository reservationRepository;

    @GetMapping("/reservations")
    public ResponseEntity<List<ReservationProjection>> getReservations(@PageableDefault(sort = {"reservationDate"}) Pageable pageable) {
        Page<ReservationProjection> page = reservationRepository.findBy(pageable);
        return new ResponseEntity<>(page.getContent(), HttpStatus.OK);
    }

    @GetMapping("/reservations/search")
    public ResponseEntity<List<ReservationProjection>> getReservationsByEmail(
            @RequestParam(name = "email") String email,
            @PageableDefault(sort = "reservationDate") Pageable pageable) {
        Page<ReservationProjection> page = reservationRepository.findReservationProjectionsByCustomers_Email(email, pageable);
        return new ResponseEntity<>(page.getContent(), HttpStatus.OK);
    }
}
