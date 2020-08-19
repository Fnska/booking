package io.fnska.booking.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerReservation {
    @EmbeddedId
    private CustomerReservationKey id;

    @ManyToOne
    @MapsId("customer_id")
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @MapsId("reservation_id")
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;
}
