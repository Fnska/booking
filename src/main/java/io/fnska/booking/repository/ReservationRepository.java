package io.fnska.booking.repository;

import io.fnska.booking.domain.Reservation;
import io.fnska.booking.repository.projections.ReservationProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Page<ReservationProjection> findBy(Pageable pageable);

    Page<ReservationProjection> findReservationProjectionsByCustomers_Email(String email, Pageable pageable);
    Page<Reservation> findReservationsByCustomers_Email(String email, Pageable pageable);
    boolean existsByReservationDate(Date reservationDate);

    @Query("SELECT r FROM Reservation r LEFT JOIN FETCH r.customers WHERE r.reservationDate = ?1")
    Reservation findOneByReservationDate(Date date);
}
