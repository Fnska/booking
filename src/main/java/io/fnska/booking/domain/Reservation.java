package io.fnska.booking.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    private Long id;

    @Column(name = "reservation_date", unique = true)
    private Date reservationDate;

    @OneToMany(mappedBy = "reservation", fetch = FetchType.EAGER)
    @JsonIgnoreProperties({"id", "reservation"})
    private List<CustomerReservation> customerReservations;
}
