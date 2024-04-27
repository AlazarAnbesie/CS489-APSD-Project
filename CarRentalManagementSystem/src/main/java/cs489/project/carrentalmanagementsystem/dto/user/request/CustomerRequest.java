package cs489.project.carrentalmanagementsystem.dto.user.request;

import cs489.project.carrentalmanagementsystem.model.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequest{
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    private LocalDate dateOfBirth;
    private Address address;
    private String driverLicenseNumber;
    private LocalDate driverLicenseExpiryDate;
    private String driverLicenseState;
}