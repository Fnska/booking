package io.fnska.booking.web.rest.v2;

import io.fnska.booking.converter.Converter;
import io.fnska.booking.converter.dto.ReservationDto;
import io.fnska.booking.domain.Reservation;
import io.fnska.booking.repository.ReservationRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2")
@AllArgsConstructor
public class ReservationControllerV2 {
    private ReservationRepository reservationRepository;
    private Converter<Reservation, ReservationDto> converter;

    @GetMapping("/reservations")
    public ResponseEntity<List<ReservationDto>> getReservations(@PageableDefault(sort = {"reservationDate"}) Pageable pageable) {
        Page<Reservation> page = reservationRepository.findAll(pageable);
        Page<ReservationDto> pageDto = converter.entityToDto(page);
        return new ResponseEntity<>(pageDto.getContent(), HttpStatus.OK);
    }

    @GetMapping("/reservations/search")
    public ResponseEntity<List<ReservationDto>> getReservationsByEmail(
            @RequestParam(name = "email") String email,
            @PageableDefault(sort = {"reservationDate"}) Pageable pageable
    ) {
        Page<Reservation> page = reservationRepository.findReservationsByCustomers_Email(email, pageable);
        Page<ReservationDto> pageDto = converter.entityToDto(page);
        return new ResponseEntity<>(pageDto.getContent(), HttpStatus.OK);
    }

    @PostMapping("/reservations")
    public ResponseEntity<ReservationDto> saveReservation(@RequestBody ReservationDto dto) {
        Reservation reservation = converter.dtoToEntity(dto);
        if (reservationRepository.existsByReservationDate(reservation.getReservationDate())){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        reservation = reservationRepository.save(reservation);
        ReservationDto dtoFromDb = converter.entityToDto(reservation);
        return new ResponseEntity<>(dtoFromDb, HttpStatus.CREATED);
    }
}
