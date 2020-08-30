package io.fnska.booking.web.rest.v2;

import io.fnska.booking.converter.Converter;
import io.fnska.booking.converter.dto.ReservationDto;
import io.fnska.booking.domain.Reservation;
import io.fnska.booking.repository.ReservationRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("integration")
public class ResetvationControllerV2IntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private Converter<Reservation, ReservationDto> converter;

    @Test
    public void whenGetRequestToReservations_thenCorrectResponse() throws Exception {
        mockMvc.perform(get("/api/v2/reservations")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void whenGetRequestToReservationsSearch_thenCorrectResponse() throws Exception {
        mockMvc.perform(get("/api/v2/reservations/search?email=ivan@ivan.com")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void whenPostRequestToReservationsAndValidReservation_thenCorrectResponse() throws Exception {
        String body = "{\"reservationDate\": \"2020-07-07\"}";
        mockMvc.perform(post("/api/v2/reservations")
                .content(body)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.reservationDate", is("2020-07-07")));
    }

    @Test
    public void whenPostRequestToReservationsAndDuplicatedReservation_thenCorrectResponse() throws Exception {
        String body = "{\"reservationDate\": \"2020-01-01\"}";
        mockMvc.perform(post("/api/v2/reservations")
                .content(body)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
