package pl.coderslab.charity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.coderslab.charity.entity.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    @Query("SELECT u FROM User u inner join u.roles r WHERE r.name='ROLE_ADMIN'")
    List<User> findAllByRoleAdmin();
    @Query("SELECT u FROM User u inner join u.roles r WHERE r.name='ROLE_USER'")
    List<User> findAllByRoleUser();
}
