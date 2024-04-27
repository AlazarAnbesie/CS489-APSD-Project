package cs489.project.carrentalmanagementsystem.dto.rent.request;

import cs489.project.carrentalmanagementsystem.model.rent.enums.RentalStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalAgreementRequest {
    private Double totalCost;

    private LocalDate startDate;

    private LocalDate endDate;

    private RentalStatus status;

    private Long paymentTransactionId;

    private Long reservationId;

    private Long vehicleId;
}
