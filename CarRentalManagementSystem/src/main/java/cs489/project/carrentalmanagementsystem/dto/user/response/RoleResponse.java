package cs489.project.carrentalmanagementsystem.dto.user.response;


import cs489.project.carrentalmanagementsystem.model.user.RoleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleResponse implements Serializable {
    private Long roleId;
    private RoleType roleType;
}