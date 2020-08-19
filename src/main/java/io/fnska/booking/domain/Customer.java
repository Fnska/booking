package io.fnska.booking.domain;

import com.fasterxml.jackson.annotation.JsonView;
import io.fnska.booking.domain.view.Views;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.CustomerAndReservationView.class)
    private Long id;

    @JsonView(Views.CustomerAndReservationView.class)
    @Column(unique = true)
    private String email;

    @JsonView(Views.CustomerAndReservationView.class)
    private String name;

    @JsonView(Views.CustomerAndReservationView.class)
    private String surname;

    @JsonView(Views.CustomerView.class)
    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
    private List<CustomerReservation> customerReservations;
}
