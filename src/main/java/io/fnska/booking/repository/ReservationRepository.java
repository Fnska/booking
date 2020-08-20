package io.fnska.booking.repository;

import io.fnska.booking.domain.Reservation;
import io.fnska.booking.domain.dto.ReservationProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Page<ReservationProjection> findBy(Pageable pageable);

    Page<ReservationProjection> findByCustomers_Email(String email, Pageable pageable);
}
