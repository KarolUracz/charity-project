package pl.coderslab.charity.fixture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.entity.Role;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.repository.RoleRepository;
import pl.coderslab.charity.repository.UserRepository;

import java.util.HashSet;
import java.util.Set;

@Service
public class InitDataFixture {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public InitDataFixture(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
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
        adminRoles.add(roleRepository.findByName("ROLE_ADMIN"));

        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword("admin");
        admin.setRoles(adminRoles);
        userRepository.save(admin);

        Set<Role> userRoles = new HashSet<>();
        userRoles.add(roleRepository.findByName("ROLE_USER"));

        User user = new User();
        user.setUsername("user");
        user.setPassword("user");
        user.setRoles(userRoles);

        userRepository.save(user);
    }
}
