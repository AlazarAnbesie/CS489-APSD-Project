package cs489.project.carrentalmanagementsystem.dto.reservation.response;

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
public class CustomerReservationResponse implements Serializable {
    Long userId;
    String firstName;
    String lastName;
    String email;
    String phoneNumber;
    LocalDate dateOfBirth;
    Address address;
    String driverLicenseNumber;
    String driverLicenseState;
    LocalDate driverLicenseExpiryDate;
}