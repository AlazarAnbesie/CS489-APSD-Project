package cs489.project.carrentalmanagementsystem.model.user;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Collection;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RentalAgent extends User{
    @NotBlank(message = "Employee ID is mandatory")
    private String employeeId;
    @NotBlank(message = "Agency name is mandatory")
    private String agencyName;




}
