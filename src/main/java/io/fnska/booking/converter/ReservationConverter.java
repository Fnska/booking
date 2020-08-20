package io.fnska.booking.converter;

import io.fnska.booking.converter.dto.ReservationDto;
import io.fnska.booking.domain.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class ReservationConverter implements Converter<Reservation, ReservationDto> {
    @Override
    public ReservationDto entityToDto(Reservation reservation) {
        ReservationDto dto = new ReservationDto();
        dto.setReservationDate(reservation.getReservationDate());
        return dto;
    }

    @Override
    public Reservation dtoToEntity(ReservationDto dto) {
        Reservation reservation = new Reservation();
        reservation.setReservationDate(dto.getReservationDate());
        return reservation;
    }

    @Override
    public Page<ReservationDto> entityToDto(Page<Reservation> reservation) {
        return reservation.map(this::entityToDto);
    }
}
