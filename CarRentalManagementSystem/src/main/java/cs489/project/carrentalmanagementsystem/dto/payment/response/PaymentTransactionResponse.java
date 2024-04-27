package cs489.project.carrentalmanagementsystem.dto.payment.response;

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
public class PaymentTransactionResponse implements Serializable {
    Long id;
    PaymentStatus paymentStatus;
    @NotNull(message = "Payment date is required")
    LocalDate paymentDate;
    PaymentType paymentType;
    @NotNull(message = "Payment amount is required")
    Double amount;
}