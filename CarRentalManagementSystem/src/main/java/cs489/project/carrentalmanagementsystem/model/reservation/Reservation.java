package cs489.project.carrentalmanagementsystem.model.reservation;

import cs489.project.carrentalmanagementsystem.model.Address;
import cs489.project.carrentalmanagementsystem.model.payment.PaymentTransaction;
import cs489.project.carrentalmanagementsystem.model.user.Customer;
import cs489.project.carrentalmanagementsystem.model.vehicle.Vehicle;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Reservation number is required")
    private String reservationNumber;
    @NotNull(message = "Start date is required")
    private LocalDate startDate;
    @NotNull(message = "End date is required")
    private LocalDate endDate;
    @NotNull(message = "Total price is required")
    private double totalPrice;
    @NotNull(message = "Reservation status is required")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pickupAddress_id")
    private Address pickupAddress;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dropoffAddress_id")
    private Address dropoffAddress;
    @ManyToOne(cascade = CascadeType.ALL)
    private Customer customer;
    @ManyToOne(cascade = CascadeType.ALL)
    private Vehicle vehicle;
    @OneToOne(cascade = CascadeType.ALL)
    private PaymentTransaction paymentTransaction;

}
