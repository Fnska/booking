package io.fnska.booking.domain;

import com.fasterxml.jackson.annotation.JsonView;
import io.fnska.booking.domain.view.Views;
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
    @JsonView(Views.ReservationView.class)
    private Customer customer;

    @ManyToOne
    @MapsId("reservation_id")
    @JoinColumn(name = "reservation_id")
    @JsonView(Views.CustomerView.class)
    private Reservation reservation;
}
