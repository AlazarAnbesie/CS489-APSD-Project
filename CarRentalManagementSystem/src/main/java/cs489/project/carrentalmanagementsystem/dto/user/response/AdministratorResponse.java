package cs489.project.carrentalmanagementsystem.dto.user.response;

import cs489.project.carrentalmanagementsystem.model.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdministratorResponse implements Serializable {
    Long userId;
    String firstName;
    String lastName;
    String username;
    String email;
    String phoneNumber;
    LocalDate dateOfBirth;
    Address address;
    List<RoleResponse> roles;
    String adminId;
}