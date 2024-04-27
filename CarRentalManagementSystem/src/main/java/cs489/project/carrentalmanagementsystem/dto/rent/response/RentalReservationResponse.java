package cs489.project.carrentalmanagementsystem.dto.rent.response;

import cs489.project.carrentalmanagementsystem.model.Address;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link cs489.project.carrentalmanagementsystem.model.reservation.Reservation}
 */
@Value
public class RentalReservationResponse implements Serializable {
    Long id;
    @NotBlank(message = "Reservation number is required")
    String reservationNumber;
    @NotNull(message = "Start date is required")
    LocalDate startDate;
    @NotNull(message = "End date is required")
    LocalDate endDate;
    double totalPrice;
    @NotNull(message = "Reservation status is required")
    Address pickupAddress;
    Address dropoffAddress;
}