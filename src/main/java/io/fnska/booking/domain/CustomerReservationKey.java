package io.fnska.booking.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerReservationKey implements Serializable {
    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "reservation_id")
    private Long reservationId;
}
