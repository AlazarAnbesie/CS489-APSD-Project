package cs489.project.carrentalmanagementsystem.controller.user;

import cs489.project.carrentalmanagementsystem.dto.user.request.RentalAgentRequest;
import cs489.project.carrentalmanagementsystem.dto.user.request.RentalAgentUpdate;
import cs489.project.carrentalmanagementsystem.dto.user.response.RentalAgentResponse;
import cs489.project.carrentalmanagementsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/agents")
public class RentalAgentController {

    @Qualifier("rentalAgentService")
    private UserService<RentalAgentRequest, RentalAgentUpdate, RentalAgentResponse> rentalAgentService;
    @GetMapping
    public ResponseEntity<?> getAllRentalAgents() {
        return ResponseEntity.ok(rentalAgentService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAgentById(@PathVariable Long id) {
        return ResponseEntity.ok(rentalAgentService.getUser(id));
    }


    @PostMapping
    public ResponseEntity<?> createAgent(@RequestBody RentalAgentRequest agentRequest) {
        return ResponseEntity.ok(rentalAgentService.createUser(agentRequest));
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateAgent(@RequestBody RentalAgentUpdate agentRequest, @PathVariable Long id) {
        return ResponseEntity.ok(rentalAgentService.updateUser(id, agentRequest));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteAgent(@PathVariable Long id) {
        rentalAgentService.deleteUser(id);
        return ResponseEntity.ok("Customer deleted successfully");
    }

    @Autowired
    public void setRentalAgentService(UserService<RentalAgentRequest, RentalAgentUpdate, RentalAgentResponse> rentalAgentService) {
        this.rentalAgentService = rentalAgentService;
    }


}
