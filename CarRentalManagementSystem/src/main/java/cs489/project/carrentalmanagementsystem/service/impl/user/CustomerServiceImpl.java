package cs489.project.carrentalmanagementsystem.service.impl.user;

import cs489.project.carrentalmanagementsystem.dto.user.request.CustomerRequest;
import cs489.project.carrentalmanagementsystem.dto.user.request.CustomerUpdate;
import cs489.project.carrentalmanagementsystem.dto.user.response.CustomerResponse;
import cs489.project.carrentalmanagementsystem.exception.UserException;
import cs489.project.carrentalmanagementsystem.model.user.Customer;
import cs489.project.carrentalmanagementsystem.model.user.Role;
import cs489.project.carrentalmanagementsystem.model.user.RoleType;
import cs489.project.carrentalmanagementsystem.repository.UserRepository;
import cs489.project.carrentalmanagementsystem.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("customerService")
public class CustomerServiceImpl implements UserService<CustomerRequest, CustomerUpdate, CustomerResponse> {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder bCrypt;
    @Override
    public CustomerResponse createUser(CustomerRequest user) {
        Customer userEntity = modelMapper.map(user, Customer.class);
        Role role = new Role();
        role.setRoleType(RoleType.CUSTOMER);
        userEntity.setRoles(List.of(role));
        userEntity.setPassword(bCrypt.encode(user.getPassword()));
        Customer savedUser = userRepository.save(userEntity);
        return modelMapper.map(savedUser, CustomerResponse.class);
    }

    @Override
    public CustomerResponse getUser(Long id) {
        Customer user = (Customer) userRepository.findByRoles_RoleTypeAndUserIdIs(RoleType.CUSTOMER,id).orElseThrow(() -> new UserException("Customer with id "+ id +" not found"));
        return modelMapper.map(user, CustomerResponse.class);
    }

    @Override
    public List<CustomerResponse> getAllUsers() {
        return userRepository.findAllByRoles_RoleType(RoleType.CUSTOMER).stream().map(user -> modelMapper.map(user, CustomerResponse.class)).toList();
    }

    @Override
    public CustomerResponse updateUser(Long id, CustomerUpdate user) {
        Customer userEntity = (Customer) userRepository.findByRoles_RoleTypeAndUserIdIs(RoleType.CUSTOMER,id).orElseThrow(() -> new UserException("Customer with id "+ id +" not found"));
        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());
        userEntity.setPhoneNumber(user.getPhoneNumber());
        userEntity.setEmail(user.getEmail());
        userEntity.setUsername(user.getUsername());
        userEntity.setPassword(bCrypt.encode(user.getPassword()));
        userEntity.setAddress(user.getAddress());
        userEntity.setDateOfBirth(user.getDateOfBirth());
        userEntity.setDriverLicenseNumber(user.getDriverLicenseNumber());
        userEntity.setDriverLicenseExpiryDate(user.getDriverLicenseExpiryDate());
        userEntity.setDriverLicenseState(user.getDriverLicenseState());
        return modelMapper.map(userEntity, CustomerResponse.class);

    }

    @Override
    public void deleteUser(Long id) {
        userRepository.findByRoles_RoleTypeAndUserIdIs(RoleType.CUSTOMER,id).ifPresent(userRepository::delete);
    }
    public CustomerServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder bCrypt) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.bCrypt = bCrypt;
    }
}
