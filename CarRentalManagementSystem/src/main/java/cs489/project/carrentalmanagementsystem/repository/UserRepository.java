package cs489.project.carrentalmanagementsystem.repository;


import cs489.project.carrentalmanagementsystem.model.user.RoleType;
import cs489.project.carrentalmanagementsystem.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    //list all users who are rental agents
    List<User> findAllByRoles_RoleType(RoleType roleType);
    Optional<User> findByRoles_RoleTypeAndUserIdIs(RoleType roleType, Long userId);
    Optional<User> findUserByUsername(String username);
    Optional<User> findUserByEmail(String email);
}
