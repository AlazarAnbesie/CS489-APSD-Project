package cs489.project.carrentalmanagementsystem.model.user;


import cs489.project.carrentalmanagementsystem.model.payment.PaymentTransaction;
import cs489.project.carrentalmanagementsystem.model.reservation.Reservation;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;
import java.util.Collection;
import java.util.List;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Customer extends User{
    @NotBlank(message = "Driver license number is mandatory")
    private String driverLicenseNumber;
    @NotBlank(message = "Driver license state is mandatory")
    private String driverLicenseState;
    @NotNull(message = "Driver license expiry date is mandatory")
    private LocalDate driverLicenseExpiryDate;
    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    private List<Reservation> reservations;
    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    private List<PaymentTransaction> paymentTransactions;

}
