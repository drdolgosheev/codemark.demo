package com.codemark.demoapp.services.implementations;

import com.codemark.demoapp.model.Status;
import com.codemark.demoapp.model.User;
import com.codemark.demoapp.model.UserRole;
import com.codemark.demoapp.repositories.RoleRepository;
import com.codemark.demoapp.repositories.UserRepository;
import com.codemark.demoapp.services.validators.EmailValidator;
import com.codemark.demoapp.services.validators.PasswordValidator;
import com.codemark.demoapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private EmailValidator emailValidator = new EmailValidator();
    private PasswordValidator passwordValidator = new PasswordValidator();

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    // Все поля обязательные для заполнения
    @Override
    public User register(User user, String roleName) {

        UserRole role;

        // Если роль существует находим, если нет, создаем новую
        if(roleRepository.existsByRoleName(roleName))
            role = roleRepository.findUserRoleByRoleName(roleName);
        else {
            role = new UserRole(roleName);
            roleRepository.save(role);
        }

        List<UserRole> roles = new ArrayList<>();
        roles.add(role);

        // Проверяем логин и пароль
        /**
        if(!checkEmail(user.getMail()))
            throw new IllegalArgumentException("Не корретктый логин");

        if(!checkPassword(user.getPassword()))
            throw new IllegalArgumentException("Не корретктый пароль");
        */

        Date date = new Date();
        user.setCreated(date);
        user.setUpdated(date);
        user.setStatus(Status.ACTIVE);
        user.setRoles(roles);
        User newUser = userRepository.save(user);


//        new Logger(Level.INFO,"New user added: " + user.toString());

        return newUser;
    }

    @Override
    public List<User> getAll() {
        List<User> users = userRepository.findAll();
//        logger.log(Level.INFO, "Found", users);
        return users;
    }

    @Override
    public User findByEmail(String email) {
        User user = null;
        try {
            user = userRepository.findUserByMail(email);
        } catch (Exception e) {
//            logger.log(Level.WARNING, "No user found");
        }
        return user;
    }

    @Override
    public User findById(int id) {
        User user = null;
        try {
            user = userRepository.findByUserId(id);
        }catch (Exception e){
//            logger.log(Level.WARNING, "No user found");
        }
        return user;
    }

    @Override
    public void delete(int id) {
        try {
            userRepository.deleteById(id);
        }catch (Exception e){
//            logger.log(Level.WARNING, "User does not exist");
        }
//        logger.log(Level.INFO, "User deleted");
    }

    @Override
    public boolean checkEmail(String email) {
        return emailValidator.validate(email);
    }

    @Override
    public boolean checkPassword(String password) {
        return passwordValidator.validate(password);
    }
}
