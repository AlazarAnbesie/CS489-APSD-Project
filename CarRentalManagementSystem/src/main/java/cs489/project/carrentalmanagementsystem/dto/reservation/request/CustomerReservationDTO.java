package cs489.project.carrentalmanagementsystem.dto.reservation.request;

import cs489.project.carrentalmanagementsystem.model.Address;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link cs489.project.carrentalmanagementsystem.model.user.Customer}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerReservationDTO implements Serializable {
    @NotBlank(message = "First name is mandatory")
    String firstName;
    @NotBlank(message = "Last name is mandatory")
    String lastName;
    @NotBlank(message = "Email is mandatory")
    String email;
    @NotBlank(message = "Phone number is mandatory")
    String phoneNumber;
    @NotNull(message = "Date of birth is mandatory")
    LocalDate dateOfBirth;
    Address address;
    @NotBlank(message = "Driver license number is mandatory")
    String driverLicenseNumber;
    @NotBlank(message = "Driver license state is mandatory")
    String driverLicenseState;
    LocalDate driverLicenseExpiryDate;
}