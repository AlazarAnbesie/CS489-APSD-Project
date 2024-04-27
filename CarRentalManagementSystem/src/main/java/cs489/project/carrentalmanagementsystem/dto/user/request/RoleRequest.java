package cs489.project.carrentalmanagementsystem.dto.user.request;


import cs489.project.carrentalmanagementsystem.model.user.RoleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleRequest implements Serializable {
    private Integer roleId;
    private RoleType roleType;
}