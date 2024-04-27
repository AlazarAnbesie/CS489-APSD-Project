package cs489.project.carrentalmanagementsystem.dto.vehicle.response;

import cs489.project.carrentalmanagementsystem.model.vehicle.enums.VehicleStatus;
import cs489.project.carrentalmanagementsystem.model.vehicle.enums.VehicleType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link cs489.project.carrentalmanagementsystem.model.vehicle.Vehicle}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleResponse implements Serializable {
    Long id;
    String make;
    String model;
    Integer year;
    Long mileage;
    String vin;
    String color;
    String plateNumber;
    VehicleStatus vehicleStatus;
    VehicleType type;
    Double rentalRate;
}

