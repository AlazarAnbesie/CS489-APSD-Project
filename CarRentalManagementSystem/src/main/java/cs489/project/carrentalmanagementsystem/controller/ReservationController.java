package cs489.project.carrentalmanagementsystem.controller;

import cs489.project.carrentalmanagementsystem.dto.reservation.request.ReservationRequest;
import cs489.project.carrentalmanagementsystem.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/reservations")
public class ReservationController {
    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService){
        this.reservationService = reservationService;
    }
    @PostMapping
    public ResponseEntity<?> createReservation(@RequestBody ReservationRequest reservationRequestDTO) {
       return ResponseEntity.ok(reservationService.createReservation(reservationRequestDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getReservationById(@PathVariable Long id) {
        return ResponseEntity.ok(reservationService.getReservation(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateReservation(@RequestBody ReservationRequest reservationRequestDTO, @PathVariable Long id) {
        return ResponseEntity.ok(reservationService.updateReservation(id,reservationRequestDTO));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole(RoleType.ADMIN, 'CUSTOMER')")
    public ResponseEntity<?> deleteReservation(@PathVariable Long id) {
        reservationService.deleteReservation(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAllReservations() {
        return ResponseEntity.ok(reservationService.getAllReservations());
    }

    @PutMapping("/{id}/extend")
    public ResponseEntity<?> extendReservation(@PathVariable Long id, @RequestBody LocalDate newEndDate) {
        return ResponseEntity.ok(reservationService.extendReservation(id, newEndDate));
    }
}