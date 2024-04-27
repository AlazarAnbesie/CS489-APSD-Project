package cs489.project.carrentalmanagementsystem.controller;


import cs489.project.carrentalmanagementsystem.dto.vehicle.request.VehicleRequest;
import cs489.project.carrentalmanagementsystem.service.VehicleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping
    public ResponseEntity<?> getAllVehicles() {
        return ResponseEntity.ok(vehicleService.getAllVehicle());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getVehicleById(@PathVariable Long id) {
        return ResponseEntity.ok(vehicleService.getVehicleById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVehicle(@PathVariable Long id) {
        vehicleService.deleteVehicle(id);
        return ResponseEntity.ok("Vehicle deleted successfully");
    }

    @PostMapping
    public ResponseEntity<?> createVehicle(@RequestBody VehicleRequest vehicle) {
        return ResponseEntity.ok(vehicleService.createVehicle(vehicle));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateVehicle(@RequestBody VehicleRequest vehicle, @PathVariable Long id) {
        return ResponseEntity.ok(vehicleService.updateVehicle(vehicle, id));
    }

    @GetMapping("/available")
    public ResponseEntity<?> getAvailableVehicles() {
        return ResponseEntity.ok(vehicleService.getAvailableVehicles());
    }


    @PutMapping("/{id}/set/rental-rate") // /api/v1/vehicles/{id}/set/rental-rate?rentalRate=100
    public ResponseEntity<?> setVehicleRentalRate(@PathVariable Long id, @RequestParam Double rentalRate) {
        return ResponseEntity.ok(vehicleService.setVehicleRentalRate(id, rentalRate));
    }

//    @PutMapping("/{id}/reserved")
//    public ResponseEntity<?> setVehicleBooked(@PathVariable Long id) {
//        return ResponseEntity.ok(vehicleService.setVehicleBooked(id));
//    }





}
