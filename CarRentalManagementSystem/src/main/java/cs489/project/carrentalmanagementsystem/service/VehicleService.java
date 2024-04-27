package cs489.project.carrentalmanagementsystem.service;





import cs489.project.carrentalmanagementsystem.dto.vehicle.request.VehicleRequest;
import cs489.project.carrentalmanagementsystem.dto.vehicle.response.VehicleResponse;

import java.util.List;
import java.util.Optional;

public interface VehicleService {

     List<VehicleResponse> getAllVehicle() ;
    VehicleResponse getVehicleById(Long id) ;
    VehicleResponse createVehicle(VehicleRequest vehicle) ;
    VehicleResponse updateVehicle(VehicleRequest vehicle, Long id);
     void deleteVehicle(Long id) ;

    List<VehicleResponse> getAvailableVehicles();

    VehicleRequest setVehicleBooked(Long id);

    VehicleResponse setVehicleRentalRate(Long id, Double rentalRate);
}
