package cs489.project.carrentalmanagementsystem.dto.user.response;

import cs489.project.carrentalmanagementsystem.model.Address;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * DTO for {@link cs489.project.carrentalmanagementsystem.model.user.Customer}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponse implements Serializable {
    Long userId;
    String firstName;
    String lastName;
    String username;
    String email;
    String phoneNumber;
    LocalDate dateOfBirth;
    Address address;
    List<RoleResponse> roles;
    String driverLicenseNumber;
    String driverLicenseState;
    LocalDate driverLicenseExpiryDate;
    List<CustomerReservationResponse> reservations;
    List<CustomerPaymentTransactionResponse> paymentTransactions;
}