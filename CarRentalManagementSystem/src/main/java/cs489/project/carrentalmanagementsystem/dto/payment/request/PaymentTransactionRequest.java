package cs489.project.carrentalmanagementsystem.dto.payment.request;

import cs489.project.carrentalmanagementsystem.model.payment.enums.PaymentStatus;
import cs489.project.carrentalmanagementsystem.model.payment.enums.PaymentType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link cs489.project.carrentalmanagementsystem.model.payment.PaymentTransaction}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentTransactionRequest implements Serializable {
    Long reservationId;
    PaymentStatus paymentStatus;
    PaymentType paymentType;
}