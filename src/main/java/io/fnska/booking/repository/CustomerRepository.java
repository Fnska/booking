package io.fnska.booking.repository;

import io.fnska.booking.domain.Customer;
import io.fnska.booking.repository.projections.CustomerProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Page<CustomerProjection> findBy(Pageable pageable);

    Page<CustomerProjection> findCustomerProjectionsByReservations_ReservationDate(Date date, Pageable pageable);

    Page<Customer> findCustomersByReservations_ReservationDate(Date date, Pageable pageable);

    boolean existsByEmail(String email);

    @Query("SELECT c FROM Customer c LEFT JOIN FETCH c.reservations WHERE c.email = ?1")
    Optional<Customer> findOneByEmail(String email);
}

