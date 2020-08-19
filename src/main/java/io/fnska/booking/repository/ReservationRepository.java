package io.fnska.booking.repository;

import io.fnska.booking.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    boolean existsReservationByReservationDate(Date reservationDate);

    @Query("SELECT r.id FROM Reservation r where r.reservationDate = ?1")
    Long getReservationIdByReservationDate(Date reservationDate);
}
