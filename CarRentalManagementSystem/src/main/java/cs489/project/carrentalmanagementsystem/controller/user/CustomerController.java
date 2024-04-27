package cs489.project.carrentalmanagementsystem.controller.user;

import cs489.project.carrentalmanagementsystem.dto.user.request.CustomerRequest;
import cs489.project.carrentalmanagementsystem.dto.user.request.CustomerUpdate;
import cs489.project.carrentalmanagementsystem.dto.user.response.CustomerResponse;
import cs489.project.carrentalmanagementsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    @Qualifier("customerService")
    private UserService<CustomerRequest, CustomerUpdate, CustomerResponse> customerService;

    @GetMapping
    public ResponseEntity<?> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable Long id) {
        return ResponseEntity.ok(customerService.getUser(id));
    }

    //
    @PostMapping
    public ResponseEntity<?> createCustomer(@RequestBody CustomerRequest customer) {
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.createUser(customer));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCustomer(@RequestBody CustomerUpdate customer, @PathVariable Long id) {
        return ResponseEntity.ok(customerService.updateUser(id, customer));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long id) {
        customerService.deleteUser(id);
        return ResponseEntity.ok("Customer deleted successfully");
    }

    @Autowired
    public void setCustomerService(UserService<CustomerRequest, CustomerUpdate, CustomerResponse> customerService) {
        this.customerService = customerService;
    }
}