package io.fnska.booking.repository;

import io.fnska.booking.domain.CustomerReservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerReservationRepository extends JpaRepository<CustomerReservation, Long> {
}
