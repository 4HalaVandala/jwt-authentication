package ru.halavandala.jwtauthentication.Repos;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.halavandala.jwtauthentication.Model.Role;
import ru.halavandala.jwtauthentication.Model.Status;
import ru.halavandala.jwtauthentication.Model.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class UserRepoTest {
    @Autowired
    private UserRepo testUserRepo;
    @Autowired
    private RoleRepo testRoleRepo;

    Date date = new Date();


    @Test
    void itShouldFindUserByUsername() {
        User user = new User();
        Role roleUser = testRoleRepo.findByName("ROLE_USER");
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(roleUser);

        user.setUsername("testUser");
        user.setEmail("testUser@mail.ru");
        user.setPasswordHash("testUser");
        user.setRoles(userRoles);
        user.setStatus(Status.ACTIVE);
        user.setCreated(date);
        user.setUpdated(date);

        testUserRepo.save(user);

        User expected = testUserRepo.findByUsername("testUser");

        assertThat(expected).isSameAs(user);
    }


}