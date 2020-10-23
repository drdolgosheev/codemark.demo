package com.codemark.demoapp.services.implementations;

import com.codemark.demoapp.model.User;
import com.codemark.demoapp.repositories.RoleRepository;
import com.codemark.demoapp.repositories.UserRepository;
import com.codemark.demoapp.services.UserService;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserServiceImplTest {

    private final UserService userService;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    User test_user;
    final String role = "ROLE_TEST";
    final String email = "test777@mail.ru";
    final String name = "testName";
    final String surname = "testSurname";
    final String password = "password1234";

    @Autowired
    UserServiceImplTest(UserService userService, UserRepository userRepository, RoleRepository roleRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @BeforeClass
    public static void globalSetUp() {
        System.out.println("Initial setup...");
    }

    @Before
    public void setUp() {
        System.out.println("Some setups...");
    }

    @Test
    void register() {
        test_user = new User(email, name, surname, password);

        // Проверяем что пользователя нет
        assertFalse(userRepository.existsByMail(email));

        // Регистрируем
        userService.register(test_user, role);

        // Проверяем, что теперь он есть
        assertTrue(userRepository.existsByMail(email));

        // Чистим репозиторий
        userRepository.delete(test_user);
    }

    @Test
    void delete() {
        test_user = new User(email, name, surname, password);

        // Регистрируем
        userService.register(test_user, role);

        // Проверяем что все хорошо
        assertTrue(userRepository.existsByMail(email));

        // Удаляем пользователя
        userService.delete(test_user.getUserId());

        // Чистим репозиторий
        assertFalse(userRepository.existsByMail(email));

    }
}