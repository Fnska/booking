package io.fnska.booking.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "reservation_date")
    private Date reservationDate;

    @ManyToMany(mappedBy = "reservations")
    private Set<Customer> customers = new HashSet<>();

    public void addCustomer(Customer customer) {
        this.customers.add(customer);
        customer.getReservations().add(this);
    }

    public void removeCustomer(Customer customer) {
        this.customers.remove(customer);
        customer.getReservations().remove(this);
    }
}
