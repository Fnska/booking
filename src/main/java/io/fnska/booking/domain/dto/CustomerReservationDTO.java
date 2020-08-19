package io.fnska.booking.domain.dto;

import lombok.Data;

@Data
public class CustomerReservationDTO {
    private String name;
    private String surname;
    private String email;
    private String date;
}
