package cs489.project.carrentalmanagementsystem.dto.rent.response;

import cs489.project.carrentalmanagementsystem.model.rent.enums.RentalStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link cs489.project.carrentalmanagementsystem.model.rent.RentalAgreement}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalAgreementResponse implements Serializable {
    Long id;
    Double totalCost;
    LocalDate startDate;
    LocalDate endDate;
    RentalStatus status;
    RentalPaymentTransactionResponse paymentTransaction;
    RentalReservationResponse reservation;
    RentalVehicleResponse vehicle;
}