package io.fnska.booking.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    private String name;

    private String surname;

    @ManyToMany
    @JoinTable(name = "customer_reservation",
            joinColumns = {@JoinColumn(name = "fk_customer")},
            inverseJoinColumns = {@JoinColumn(name = "fk_reservation")})
    private Set<Reservation> reservations = new HashSet<>();

    public void addReservation(Reservation reservation) {
        this.reservations.add(reservation);
        reservation.getCustomers().add(this);
    }

    public void removeReservation(Reservation reservation) {
        this.reservations.remove(reservation);
        reservation.getCustomers().remove(this);
    }
}
