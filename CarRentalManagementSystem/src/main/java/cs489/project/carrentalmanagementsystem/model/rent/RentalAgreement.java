package cs489.project.carrentalmanagementsystem.model.rent;

import cs489.project.carrentalmanagementsystem.model.payment.PaymentTransaction;
import cs489.project.carrentalmanagementsystem.model.rent.enums.RentalStatus;
import cs489.project.carrentalmanagementsystem.model.reservation.Reservation;
import cs489.project.carrentalmanagementsystem.model.vehicle.Vehicle;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RentalAgreement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Total cost is required")
    private Double totalCost;
    @NotNull(message = "Start date is required")
    private LocalDate startDate;
    @NotNull(message = "End date is required")
    private LocalDate endDate;
    @NotNull(message = "Status is required")
    private RentalStatus status;
    @OneToOne
    private PaymentTransaction paymentTransaction;
    @ManyToOne
    private Vehicle vehicle;
    @OneToOne
    private Reservation reservation;

}
