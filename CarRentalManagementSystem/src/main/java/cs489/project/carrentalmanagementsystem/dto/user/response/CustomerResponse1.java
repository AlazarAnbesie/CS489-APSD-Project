package cs489.project.carrentalmanagementsystem.dto.user.response;

import cs489.project.carrentalmanagementsystem.model.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;


//public record CustomerResponse(Long userId, String firstName, String lastName, String username, String email,
//                               String phoneNumber, LocalDate dateOfBirth, Address address, List<RoleResponse> roles,
//                               String driverLicenseNumber, String driverLicenseState, List<Long> paymentTransactionIds,
//                               List<Long> reservationIds) implements Serializable {
//}

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponse1 implements Serializable {
    private Long userId;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String phoneNumber;
    private LocalDate dateOfBirth;
    private Address address;
    private List<RoleResponse> roles;
    private String driverLicenseNumber;
    private String driverLicenseState;

}