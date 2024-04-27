package cs489.project.carrentalmanagementsystem.dto.rent.response;

import cs489.project.carrentalmanagementsystem.dto.user.response.CustomerResponse1;
import cs489.project.carrentalmanagementsystem.model.payment.enums.PaymentStatus;
import cs489.project.carrentalmanagementsystem.model.payment.enums.PaymentType;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link cs489.project.carrentalmanagementsystem.model.payment.PaymentTransaction}
 */
@Value
public class RentalPaymentTransactionResponse implements Serializable {
    Long id;
    PaymentStatus paymentStatus;
    @NotNull(message = "Payment date is required")
    LocalDate paymentDate;
    PaymentType paymentType;
    @NotNull(message = "Payment amount is required")
    Double amount;
    CustomerResponse1 customer;
}