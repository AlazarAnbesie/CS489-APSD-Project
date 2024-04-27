package cs489.project.carrentalmanagementsystem.dto.reservation.response;

import cs489.project.carrentalmanagementsystem.model.Address;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link cs489.project.carrentalmanagementsystem.model.reservation.Reservation}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationResponse implements Serializable {
    Long id;
    String reservationNumber;
    LocalDate startDate;
    LocalDate endDate;
    double totalPrice;
    Address pickupAddress;
    Address dropoffAddress;
    CustomerReservationResponse customer;
    VehicleReservationResponse vehicle;
}