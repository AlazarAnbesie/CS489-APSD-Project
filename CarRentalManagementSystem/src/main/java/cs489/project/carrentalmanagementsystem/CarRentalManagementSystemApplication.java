package cs489.project.carrentalmanagementsystem;

import cs489.project.carrentalmanagementsystem.model.Address;
import cs489.project.carrentalmanagementsystem.model.user.Administrator;
import cs489.project.carrentalmanagementsystem.model.user.Role;
import cs489.project.carrentalmanagementsystem.model.user.RoleType;
import cs489.project.carrentalmanagementsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class CarRentalManagementSystemApplication implements CommandLineRunner {

	private UserRepository userRepository;
	private PasswordEncoder bCryptPasswordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(CarRentalManagementSystemApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Administrator admin = new Administrator();
		admin.setUsername("admin");
		admin.setPassword(bCryptPasswordEncoder.encode("admin"));
		admin.setEmail("admin@apsd.com");
		admin.setAdminId("ADMIN-001");
		admin.setRoles(List.of(new Role(null, RoleType.ADMIN, null)));
		admin.setAddress(new Address(null,"1000 N 4th St", "Fairfield", "Iowa", "52557"));
		admin.setDateOfBirth(LocalDate.of(1990, 1, 1));
		admin.setFirstName("Supreme");
		admin.setLastName("Leader");
		admin.setPhoneNumber("641-451-1234");
		userRepository.save(admin);
		System.out.println("Server is up and running...");
	}

	@Autowired
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Autowired
	public void setbCryptPasswordEncoder(PasswordEncoder bCryptPasswordEncoder) {
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}
}
