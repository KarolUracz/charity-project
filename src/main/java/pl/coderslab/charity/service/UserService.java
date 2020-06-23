package pl.coderslab.charity.service;

import pl.coderslab.charity.entity.User;

import java.util.List;

public interface UserService {
    User findByUserName (String name);
    void saveUser(User user);
    User findById(Long id);
    void deleteUser(Long id);
    void saveAdmin(User user);
    List<User> findAllAdministrators();
    List<User> findAllUsers();
    void save(User user);
    void editUser(User user);
    void changePassword(User user);
}
