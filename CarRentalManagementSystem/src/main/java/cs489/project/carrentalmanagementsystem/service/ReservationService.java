package cs489.project.carrentalmanagementsystem.service;

import cs489.project.carrentalmanagementsystem.dto.reservation.request.ReservationRequest;
import cs489.project.carrentalmanagementsystem.dto.reservation.response.ReservationResponse;

import java.time.LocalDate;
import java.util.List;

public interface ReservationService {
    ReservationResponse createReservation(ReservationRequest reservationRequest);
    ReservationResponse getReservation(Long id);
    List<ReservationResponse> getAllReservations();
    ReservationResponse updateReservation(Long id, ReservationRequest reservationRequest);
    void deleteReservation(Long id);

    ReservationResponse extendReservation(Long id, LocalDate newEndDate);
}
