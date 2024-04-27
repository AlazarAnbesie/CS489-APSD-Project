package cs489.project.carrentalmanagementsystem.service.impl.user;


import cs489.project.carrentalmanagementsystem.dto.user.request.AdministratorRequest;
import cs489.project.carrentalmanagementsystem.dto.user.request.AdministratorUpdate;
import cs489.project.carrentalmanagementsystem.dto.user.response.AdministratorResponse;
import cs489.project.carrentalmanagementsystem.exception.UserException;
import cs489.project.carrentalmanagementsystem.model.user.Administrator;
import cs489.project.carrentalmanagementsystem.model.user.Role;
import cs489.project.carrentalmanagementsystem.model.user.RoleType;
import cs489.project.carrentalmanagementsystem.repository.UserRepository;
import cs489.project.carrentalmanagementsystem.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("administratorService")
public class AdministratorServiceImpl implements UserService<AdministratorRequest, AdministratorUpdate, AdministratorResponse> {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder bCryptPasswordEncoder;

    @Override
    public AdministratorResponse createUser(AdministratorRequest user) {
        Administrator userEntity = modelMapper.map(user, Administrator.class);
        userEntity.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Role role = new Role();
        role.setRoleType(RoleType.ADMIN);
        userEntity.setRoles(List.of(role));
        Administrator savedUser = userRepository.save(userEntity);
        return modelMapper.map(savedUser, AdministratorResponse.class);
    }

    @Override
    public AdministratorResponse getUser(Long id) {
        Administrator user = (Administrator) userRepository.findByRoles_RoleTypeAndUserIdIs(RoleType.ADMIN,id).orElseThrow(() -> new UserException("Administrator with id "+ id +" not found"));
        return modelMapper.map(user, AdministratorResponse.class);
    }

    @Override
    public List<AdministratorResponse> getAllUsers() {
        return userRepository.findAllByRoles_RoleType(RoleType.ADMIN).stream().map(user -> modelMapper.map(user, AdministratorResponse.class)).toList();
    }

    @Override
    public AdministratorResponse updateUser(Long id, AdministratorUpdate user) {
        Administrator userEntity = (Administrator) userRepository.findByRoles_RoleTypeAndUserIdIs(RoleType.ADMIN,id).orElseThrow(() -> new UserException("Administrator with id "+ id +" not found"));
        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());
        userEntity.setPhoneNumber(user.getPhoneNumber());
        userEntity.setEmail(user.getEmail());
        userEntity.setUsername(user.getUsername());
        userEntity.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userEntity.setAddress(user.getAddress());
        userEntity.setRoles(user.getRoles().stream().map(role -> modelMapper.map(role, Role.class)).toList());
        return modelMapper.map(userEntity, AdministratorResponse.class);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.findByRoles_RoleTypeAndUserIdIs(RoleType.ADMIN,id).ifPresent(userRepository::delete);
    }

    @Autowired
    public AdministratorServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
}
