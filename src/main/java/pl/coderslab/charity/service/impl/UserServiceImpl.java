package pl.coderslab.charity.service.impl;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.entity.Role;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.service.UserService;
import pl.coderslab.charity.repository.RoleRepository;
import pl.coderslab.charity.repository.UserRepository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User findByUserName(String name) {
        return userRepository.findByUsername(name);
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(0);
        Role userRole = roleRepository.findOneByName("ROLE_USER");
        user.setRoles(new HashSet<>(Arrays.asList(userRole)));
        userRepository.save(user);
    }

    @Override
    public User findById(Long id) {
        return userRepository.getOne(id);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void saveAdmin(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(1);
        Role userRole = roleRepository.findOneByName("ROLE_ADMIN");
        user.setRoles(new HashSet<>(Arrays.asList(userRole)));
        userRepository.save(user);
    }

    @Override
    public List<User> findAllAdministrators() {
        return userRepository.findAllByRoleAdmin();
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAllByRoleUser();
    }

    @Override
    public void save(User user) {
        User userFromDB = userRepository.getOne(user.getId());
        user.setPassword(userFromDB.getPassword());
        user.setRoles(userFromDB.getRoles());
        userRepository.save(user);
    }

    @Override
    public void editUser(User user) {
        User fromDb = userRepository.getOne(user.getId());
        user.setEnabled(fromDb.getEnabled());
        user.setRoles(fromDb.getRoles());
        user.setPassword(fromDb.getPassword());
        userRepository.save(user);
    }

    @Override
    public void changePassword(User user) {
        User fromDb = userRepository.getOne(user.getId());
        user.setRoles(fromDb.getRoles());
        user.setEnabled(fromDb.getEnabled());
        user.setUsername(fromDb.getUsername());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public void resetPassword(String username, String password) {
        User fromDb = userRepository.findByUsername(username);
        fromDb.setPassword(passwordEncoder.encode(password));
        userRepository.save(fromDb);
    }
}
