package cs489.project.carrentalmanagementsystem.controller;


import cs489.project.carrentalmanagementsystem.dto.rent.request.RentalAgreementRequest;
import cs489.project.carrentalmanagementsystem.service.RentalAgreementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/rentals")
public class RentalAgreementController {
    private final RentalAgreementService rentalService;

    public RentalAgreementController(RentalAgreementService rentalService) {
        this.rentalService = rentalService;
    }
    @GetMapping
    public ResponseEntity<?> getAllRentals() {
        return ResponseEntity.ok(rentalService.getAllRentals());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRentalById(@PathVariable Long id) {
        return ResponseEntity.ok(rentalService.getRentalById(id));
    }

    @PostMapping
    public ResponseEntity<?> createRental(@RequestBody RentalAgreementRequest rentalRequestDTO) {
        return ResponseEntity.ok(rentalService.createRental(rentalRequestDTO));
    }

    @PutMapping("/{id}")
    public String updateRental(@PathVariable Long id) {
        return "updateRental";
    }

//    @PostMapping("/{id}/extend")
//    public ResponseEntity<?>  extendRental(@PathVariable Long id,@RequestBody LocalDate newEndDate) {
//        try{
//            String result = rentalService.extendRental(id, newEndDate);
//            return new ResponseEntity<>(result, HttpStatus.OK);
//        }catch (RentalException e){
//            return ResponseEntity.badRequest().body(e.getMessage()+" : Failed to extend rental");
//        }
//    }



    @PostMapping("/{id}/cancel")
    public ResponseEntity<?> cancelRental(@PathVariable Long id) {
        return ResponseEntity.ok(rentalService.cancelRental(id));
    }

    @PostMapping("/{id}/return")
    public String returnRental(@PathVariable Long id) {
        return "returnRental";
    }
}
