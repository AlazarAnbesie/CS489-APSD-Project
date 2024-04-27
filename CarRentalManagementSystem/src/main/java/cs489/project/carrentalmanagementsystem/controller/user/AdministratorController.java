package cs489.project.carrentalmanagementsystem.controller.user;

import cs489.project.carrentalmanagementsystem.dto.user.request.AdministratorRequest;
import cs489.project.carrentalmanagementsystem.dto.user.request.AdministratorUpdate;
import cs489.project.carrentalmanagementsystem.dto.user.response.AdministratorResponse;
import cs489.project.carrentalmanagementsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/administrators")
public class AdministratorController {
    @Qualifier("administratorService")
    UserService<AdministratorRequest, AdministratorUpdate, AdministratorResponse> administratorService;

    @GetMapping
    public ResponseEntity<?> getAllAdministrators() {
        return ResponseEntity.ok(administratorService.getAllUsers());
    }
    @PostMapping
    public ResponseEntity<?> createAdministrator(@RequestBody AdministratorRequest administrator) {
        return ResponseEntity.status(HttpStatus.CREATED).body(administratorService.createUser(administrator));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAdministrator(@RequestBody AdministratorUpdate administrator, @PathVariable Long id) {
        return ResponseEntity.ok(administratorService.updateUser(id, administrator));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAdministratorById(@PathVariable Long id) {
        return ResponseEntity.ok(administratorService.getUser(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAdministrator(@PathVariable Long id) {
        administratorService.deleteUser(id);
        return ResponseEntity.ok("Administrator deleted successfully");
    }
    //get user by username implementation here

    @Autowired
    public void setAdministratorService(UserService<AdministratorRequest, AdministratorUpdate, AdministratorResponse> administratorService) {
        this.administratorService = administratorService;
    }
}

