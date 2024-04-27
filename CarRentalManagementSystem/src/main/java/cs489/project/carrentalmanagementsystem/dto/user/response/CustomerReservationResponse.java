package cs489.project.carrentalmanagementsystem.dto.user.response;

import cs489.project.carrentalmanagementsystem.model.Address;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link cs489.project.carrentalmanagementsystem.model.reservation.Reservation}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerReservationResponse implements Serializable {
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