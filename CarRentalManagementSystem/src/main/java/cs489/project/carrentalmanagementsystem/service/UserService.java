package cs489.project.carrentalmanagementsystem.service;



import java.util.List;

public interface UserService<T,U,R> {
    R createUser(T user);
    R getUser(Long id);
    List<R> getAllUsers();
    R updateUser(Long id, U user);
    void deleteUser(Long id);
}
