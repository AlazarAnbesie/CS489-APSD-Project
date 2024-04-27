package cs489.project.carrentalmanagementsystem.config;

import cs489.project.carrentalmanagementsystem.security.CarRentalUserDetailService;
import cs489.project.carrentalmanagementsystem.security.filter.JWTAuthFilter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class JavaConfig {
    private final CarRentalUserDetailService carRentalUserDetailService;
    private final JWTAuthFilter jwtAuthFilter;

    public JavaConfig(CarRentalUserDetailService carRentalUserDetailService, JWTAuthFilter jwtAuthFilter) {
        this.carRentalUserDetailService = carRentalUserDetailService;
        this.jwtAuthFilter = jwtAuthFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeRequests(authorizeRequests -> {
                    authorizeRequests
                            .requestMatchers("/api/v1/auth/login").permitAll()
                            .requestMatchers("/api/v1/**").authenticated();

                })
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(carRentalUserDetailService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();

    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
    // Customer's authorization is as follows
    // The customer can register
    // The customer can view their profile
    // The customer can update their profile
    // The customer can delete their profile
    // The customer can view their reservations
    // The customer can make a reservation
    // The customer can update a reservation
    // The customer can delete a reservation
    // the requestmatchers would be /api/v1/customers/** and the role would be CUSTOMER
    // The admin's authorization is as follows
    // The admin can view all customers
    // The admin can view a customer
    // The admin can update a customer
    // The admin can delete a customer
    // The admin can view all reservations
    // The admin can view a reservation
    // The admin can update a reservation
    // The admin can delete a reservation
    // the requestmatchers would be /api/v1/customers/** and the role would be ADMIN
    // the rental agent authorization is as follows
    // The rental agent can view all customers
    // The rental agent can view a customer
    // The rental agent can update a customer
    // The rental agent can delete a customer
    // The rental agent can view all reservations
    // The rental agent can view a reservation
    // The rental agent can update a reservation
    // The customer can manage their own profile and reservations
    // Rental agent can manage reservations except deleting a reservation
    // renatl agent can manage customers except deleting a customer
    // rental agent can manage vehicles

}
