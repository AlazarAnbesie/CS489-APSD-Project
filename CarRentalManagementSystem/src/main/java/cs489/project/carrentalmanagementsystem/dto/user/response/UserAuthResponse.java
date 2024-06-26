package cs489.project.carrentalmanagementsystem.dto.user.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAuthResponse {
    private String username;
    private String password;
    private List<RoleResponse> roles;
}
