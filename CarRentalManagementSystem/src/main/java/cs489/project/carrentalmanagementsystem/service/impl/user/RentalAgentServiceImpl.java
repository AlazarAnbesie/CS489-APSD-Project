package cs489.project.carrentalmanagementsystem.service.impl.user;


import cs489.project.carrentalmanagementsystem.dto.user.request.RentalAgentRequest;
import cs489.project.carrentalmanagementsystem.dto.user.request.RentalAgentUpdate;
import cs489.project.carrentalmanagementsystem.dto.user.response.RentalAgentResponse;
import cs489.project.carrentalmanagementsystem.exception.UserException;
import cs489.project.carrentalmanagementsystem.model.user.RentalAgent;
import cs489.project.carrentalmanagementsystem.model.user.Role;
import cs489.project.carrentalmanagementsystem.model.user.RoleType;
import cs489.project.carrentalmanagementsystem.repository.UserRepository;
import cs489.project.carrentalmanagementsystem.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("rentalAgentService")
public class RentalAgentServiceImpl implements UserService<RentalAgentRequest, RentalAgentUpdate, RentalAgentResponse> {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder bCrypt;
    @Override
    public RentalAgentResponse createUser(RentalAgentRequest user) {
        RentalAgent userEntity = modelMapper.map(user, RentalAgent.class);
        userEntity.setPassword(bCrypt.encode(user.getPassword()));
        Role role = new Role();
        role.setRoleType(RoleType.AGENT);
        userEntity.setRoles(List.of(role));
        RentalAgent savedUser = userRepository.save(userEntity);
        return modelMapper.map(savedUser, RentalAgentResponse.class);
    }

    @Override
    public RentalAgentResponse getUser(Long id) {
        RentalAgent user = (RentalAgent) userRepository.findByRoles_RoleTypeAndUserIdIs(RoleType.AGENT,id).orElseThrow(() -> new UserException("Rental agent with id "+ id +" not found"));
        return modelMapper.map(user, RentalAgentResponse.class);
    }

    @Override
    public List<RentalAgentResponse> getAllUsers() {
        return userRepository.findAllByRoles_RoleType(RoleType.AGENT).stream().map(user -> modelMapper.map(user, RentalAgentResponse.class)).toList();

    }

    @Override
    public RentalAgentResponse updateUser(Long id, RentalAgentUpdate user) {
        RentalAgent userEntity = (RentalAgent) userRepository.findByRoles_RoleTypeAndUserIdIs(RoleType.AGENT,id).orElseThrow(() -> new UserException("Rental agent with id "+ id +" not found"));
        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());
        userEntity.setPhoneNumber(user.getPhoneNumber());
        userEntity.setEmail(user.getEmail());
        userEntity.setUsername(user.getUsername());
        userEntity.setPassword(bCrypt.encode(user.getPassword()));
        userEntity.setAddress(user.getAddress());
        userEntity.setDateOfBirth(user.getDateOfBirth());
        userEntity.setAgencyName(user.getAgencyName());
        return modelMapper.map(userEntity, RentalAgentResponse.class);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.findByRoles_RoleTypeAndUserIdIs(RoleType.AGENT,id).ifPresent(userRepository::delete);
    }

    public RentalAgentServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder bCrypt) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.bCrypt = bCrypt;
    }

}
