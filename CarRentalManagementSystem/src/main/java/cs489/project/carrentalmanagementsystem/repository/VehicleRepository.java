package cs489.project.carrentalmanagementsystem.repository;

import cs489.project.carrentalmanagementsystem.model.vehicle.Vehicle;
import cs489.project.carrentalmanagementsystem.model.vehicle.enums.VehicleStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    Collection<Vehicle> findAllByVehicleStatus(VehicleStatus vehicleStatus);
}
