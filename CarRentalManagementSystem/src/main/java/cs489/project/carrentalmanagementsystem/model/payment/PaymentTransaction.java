package cs489.project.carrentalmanagementsystem.model.payment;

import cs489.project.carrentalmanagementsystem.model.rent.RentalAgreement;
import cs489.project.carrentalmanagementsystem.model.payment.enums.PaymentStatus;
import cs489.project.carrentalmanagementsystem.model.payment.enums.PaymentType;
import cs489.project.carrentalmanagementsystem.model.user.Customer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate paymentDate;
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;
    private Double amount;
    @ManyToOne(cascade = CascadeType.ALL)
    private Customer customer;
    @OneToOne
    private RentalAgreement rentalAgreement;
    // Additional fields like amount, date, etc.

    //
}
