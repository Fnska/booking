package io.fnska.booking.domain;

import com.fasterxml.jackson.annotation.JsonView;
import io.fnska.booking.domain.view.Views;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.CustomerAndReservationView.class)
    private Long id;

    @Column(name = "reservation_date", unique = true)
    @JsonView(Views.CustomerAndReservationView.class)
    private Date reservationDate;


    @JsonView(Views.ReservationView.class)
    @OneToMany(mappedBy = "reservation", fetch = FetchType.EAGER)
    private List<CustomerReservation> customerReservations;
}
