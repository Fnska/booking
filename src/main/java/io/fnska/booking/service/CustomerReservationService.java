package io.fnska.booking.service;

import io.fnska.booking.domain.Customer;
import io.fnska.booking.domain.CustomerReservation;
import io.fnska.booking.domain.CustomerReservationKey;
import io.fnska.booking.domain.Reservation;
import io.fnska.booking.domain.dto.CustomerReservationDTO;
import io.fnska.booking.repository.CustomerRepository;
import io.fnska.booking.repository.CustomerReservationRepository;
import io.fnska.booking.repository.CustomerIdNameSurnameView;
import io.fnska.booking.repository.ReservationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class CustomerReservationService {
    private CustomerRepository customerRepository;
    private ReservationRepository reservationRepository;
    private CustomerReservationRepository customerReservationRepository;

    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    public CustomerReservation saveCustomerReservation(CustomerReservationDTO customerReservationDTO) {
        Customer customer = saveCustomer(
                                        customerReservationDTO.getName(),
                                        customerReservationDTO.getSurname(),
                                        customerReservationDTO.getEmail()
                                        );
        Reservation reservation = saveReservation(customerReservationDTO.getDate());
        CustomerReservation customerRegistration = getCustomerReservation(customer, reservation);
        return customerReservationRepository.save(customerRegistration);
    }

    private Customer saveCustomer(String name, String surname, String email) {
        Customer customer = new Customer();
        customer.setName(name);
        customer.setSurname(surname);
        customer.setEmail(email);
        boolean emailExists = customerRepository.existsCustomerByEmail(email);
        if (emailExists) {
            CustomerIdNameSurnameView customerView = customerRepository.findByEmail(email);
            customer.setId(customerView.getId());
            customer.setName(customerView.getName());
            customer.setSurname(customerView.getSurname());
        }
        return customerRepository.save(customer);
    }

    private Reservation saveReservation(String date) {
        final Date reservationDate = Date.valueOf(date);
        Reservation reservation = new Reservation();
        reservation.setReservationDate(reservationDate);
        boolean dateExists = reservationRepository.existsReservationByReservationDate(reservationDate);
        if (dateExists) {
            Long id = reservationRepository.getReservationIdByReservationDate(reservationDate);
            reservation.setId(id);
        }
        return reservationRepository.save(reservation);
    }

    private CustomerReservation getCustomerReservation(Customer customer, Reservation reservation) {
        CustomerReservation customerRegistration = new CustomerReservation();
        customerRegistration.setId(new CustomerReservationKey(customer.getId(), reservation.getId()));
        customerRegistration.setCustomer(customer);
        customerRegistration.setReservation(reservation);
        return customerRegistration;
    }
}

