package cs489.project.carrentalmanagementsystem.dto.reservation.request;

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
public class VehicleReservationRequest implements Serializable {
    @NotBlank(message = "Vehicle make is required")
    String make;
    @NotBlank(message = "Vehicle model is required")
    String model;
    Integer year;
    Long mileage;
    @NotBlank(message = "Vehicle Identification is required")
    String vin;
    @NotBlank(message = "Vehicle Color is required")
    String color;
    @NotBlank(message = "Vehicle plate number is required")
    String plateNumber;
    VehicleStatus availability;
    VehicleType type;
    @NotNull(message = "rental rate is required")
    Double rentalRate;
}