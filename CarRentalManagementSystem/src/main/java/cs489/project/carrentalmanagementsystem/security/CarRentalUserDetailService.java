package cs489.project.carrentalmanagementsystem.security;


import cs489.project.carrentalmanagementsystem.dto.user.response.RoleResponse;
import cs489.project.carrentalmanagementsystem.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarRentalUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    public CarRentalUserDetailService(UserRepository userRepository, @Lazy ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var userFromDB = userRepository.findUserByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        String userName = userFromDB.getUsername();
        String password = userFromDB.getPassword();
           List<RoleResponse> roles = userFromDB.getRoles().stream().map(role->{
                return modelMapper.map(role, RoleResponse.class);
            }).toList();
        String rolesInOne = roles.stream().map(r->r.getRoleType().toString()).reduce("",(a,b)->a+","+b);
        return User.withUsername(userName)
                .password(password)
                .roles(rolesInOne)
                .build();
    }
}
