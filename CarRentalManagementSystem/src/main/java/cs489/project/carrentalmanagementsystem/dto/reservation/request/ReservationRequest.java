package cs489.project.carrentalmanagementsystem.dto.reservation.request;

import cs489.project.carrentalmanagementsystem.model.Address;
import cs489.project.carrentalmanagementsystem.model.payment.enums.PaymentType;
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
public class ReservationRequest implements Serializable {
    @NotBlank(message = "Reservation number is required")
    String reservationNumber;
    @NotNull(message = "Start date is required")
    LocalDate startDate;
    @NotNull(message = "End date is required")
    LocalDate endDate;
    @NotNull(message = "Reservation status is required")
    Address pickupAddress;
    Address dropoffAddress;
    Long vehicleId;
    PaymentType paymentType;
    CustomerReservationDTO customer;

}