package pl.coderslab.charity.fixture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.entity.Role;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.interfaces.UserService;
import pl.coderslab.charity.repository.RoleRepository;


import java.util.HashSet;
import java.util.Set;

@Service
public class InitDataFixture {

    private final RoleRepository roleRepository;
    private final UserService userService;

    @Autowired
    public InitDataFixture(RoleRepository roleRepository, UserService userService) {
        this.roleRepository = roleRepository;
        this.userService = userService;
    }

    public void initRoles() {
        Role roleAdmin = new Role();
        roleAdmin.setName("ROLE_ADMIN");
        roleRepository.save(roleAdmin);

        Role roleUser = new Role();
        roleUser.setName("ROLE_USER");
        roleRepository.save(roleUser);
    }

    public void initUsers() {

        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(roleRepository.findOneByName("ROLE_ADMIN"));

        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword("admin");
        admin.setEnabled(1);
        admin.setRoles(adminRoles);
        userService.saveUser(admin);

        Set<Role> userRoles = new HashSet<>();
        userRoles.add(roleRepository.findOneByName("ROLE_USER"));

        User user = new User();
        user.setUsername("user");
        user.setPassword("user");
        user.setEnabled(1);
        user.setRoles(userRoles);

        userService.saveUser(user);
    }
}
