package cs489.project.carrentalmanagementsystem.model.vehicle;

import cs489.project.carrentalmanagementsystem.model.Address;
import cs489.project.carrentalmanagementsystem.model.rent.RentalAgreement;
import cs489.project.carrentalmanagementsystem.model.reservation.Reservation;
import cs489.project.carrentalmanagementsystem.model.vehicle.enums.VehicleStatus;
import cs489.project.carrentalmanagementsystem.model.vehicle.enums.VehicleType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Vehicle make is required")
    private String make;            // Make of the vehicle (e.g., "Toyota")
    @NotBlank(message = "Vehicle model is required")
    private String model;           // Model of the vehicle (e.g., "Camry")
    @NotNull(message = "Vehicle Year is required")
    private Integer year;               // Year of manufacture (e.g., 2022)
    @NotNull(message = "Vehicle Mileage is required")
    private Long mileage;            // Mileage of the vehicle (e.g., 50000)
    @NotBlank(message = "Vehicle Identification is required")
    private String vin;             // Vehicle Identification Number (e.g., "1HGCM82633A123456")
    @NotBlank(message = "Vehicle Color is required")
    private String color;           // Color of the vehicle (e.g., "Blue")
    @NotBlank(message = "Vehicle plate number is required")
    private String plateNumber;     // License plate number (e.g., "ABC123")
    @NotNull(message = "Vehicle Availability is required")
    private VehicleStatus vehicleStatus;   // Availability status of the vehicle (e.g., "Rented")
    @NotNull(message = "Vehicle Type is required")
    private VehicleType type;            // Type of vehicle (e.g., "Sedan", "SUV", "Truck", "Van")
    @NotNull(message = "rental rate is required")
    private Double rentalRate;
    @OneToMany
    private List<Reservation> reservations;
    @OneToMany
    private List<RentalAgreement> rentalAgreements;



}
