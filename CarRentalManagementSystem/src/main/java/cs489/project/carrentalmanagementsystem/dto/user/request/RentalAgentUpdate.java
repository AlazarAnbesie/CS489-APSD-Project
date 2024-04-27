package cs489.project.carrentalmanagementsystem.dto.user.request;

import cs489.project.carrentalmanagementsystem.model.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalAgentUpdate {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String username;
    private String password;
    private LocalDate dateOfBirth;
    private Address address;
    private String agencyName;
}
