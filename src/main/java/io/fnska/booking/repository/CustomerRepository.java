package io.fnska.booking.repository;

import io.fnska.booking.domain.Customer;
import io.fnska.booking.domain.dto.CustomerProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Page<CustomerProjection> findBy(Pageable pageable);

    Page<CustomerProjection> findByReservations_ReservationDate(Date date, Pageable pageable);
}

