package cs489.project.carrentalmanagementsystem.controller.user;

import cs489.project.carrentalmanagementsystem.dto.user.UserAuthRequest;
import cs489.project.carrentalmanagementsystem.security.utils.JWTManagementUtilityService;
import jakarta.validation.Valid;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class UserAuthenticationController {
    private final JWTManagementUtilityService jwtManagementUtilityService;
    private final AuthenticationManager authenticationManager;

    public UserAuthenticationController(JWTManagementUtilityService jwtManagementUtilityService, AuthenticationManager authenticationManager) {
        this.jwtManagementUtilityService = jwtManagementUtilityService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public String authenticateUser(@Valid @RequestBody UserAuthRequest userAuthRequest){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userAuthRequest.getUsername(), userAuthRequest.getPassword()));
        return jwtManagementUtilityService.generateToken(userAuthRequest.getUsername());
    }
}
