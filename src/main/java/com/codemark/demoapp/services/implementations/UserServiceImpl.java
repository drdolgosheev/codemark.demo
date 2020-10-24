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
import org.springframework.cache.support.NullValue;
import org.springframework.lang.Nullable;
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
    public User register(User user, List<String> roleNames) {

        List<UserRole> roles = new ArrayList<>();

        for (int i = 0; i < roleNames.size(); i++) {
            if(roleRepository.existsByRoleName(roleNames.get(i)))
                roles.add(roleRepository.findUserRoleByRoleName(roleNames.get(i)));
            else {
                roles.add(new UserRole(roleNames.get(i)));
                roleRepository.save(roles.get(i));
            }
        }

        // Проверяем логин (работает, если хотите можно раскоментировать)
        /**
        if(!checkEmail(user.getMail()))
            throw new IllegalArgumentException("Не корретктый логин");
         */

        // Проверяем пароль
        if(!checkPassword(user.getPassword()))
            throw new IllegalArgumentException("Не корретктый пароль");

        Date date = new Date();
        user.setCreated(date);
        user.setUpdated(date);
        user.setStatus(Status.ACTIVE);
        user.setRoles(roles);
        User newUser = userRepository.save(user);

        return newUser;
    }

    @Override
    public User editUser(User user, List<String> roleNames) {
        List<UserRole> roles = new ArrayList<>();

        for (int i = 0; i < roleNames.size(); i++) {
            if(roleRepository.existsByRoleName(roleNames.get(i)))
                roles.add(roleRepository.findUserRoleByRoleName(roleNames.get(i)));
            else {
                roles.add(new UserRole(roleNames.get(i)));
                roleRepository.save(roles.get(i));
            }
        }

        User user_old = userRepository.findUserByMail(user.getMail());
        try {
            user_old.setRoles(roles);
            user_old.setMail(user.getMail());
            user_old.setName(user.getName());
            user_old.setSurname(user.getSurname());
            user_old.setPassword(user.getPassword());
        }catch (Exception e){
            throw new IllegalArgumentException("Не все данные заполнены!");
        }

        userRepository.save(user_old);

        return user_old;
    }

    @Override
    public List<User> getAll() {
        List<User> users = userRepository.findAll();
        return users;
    }

    @Override
    public User findByEmail(String email) {
        User user = null;
        try {
            user = userRepository.findUserByMail(email);
        } catch (Exception e) {
            throw new IllegalArgumentException("Данного пользователя не существует");
        }
        return user;
    }

    @Override
    public User findById(int id) {
        User user = null;
        try {
            user = userRepository.findByUserId(id);
        }catch (Exception e){
            throw new IllegalArgumentException("Данного пользователя не существует");
        }
        return user;
    }

    @Override
    public void delete(int id) {
        try {
            userRepository.deleteById(id);
        }catch (Exception e){
            throw new IllegalArgumentException("Данного пользователя не существует");
        }
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
