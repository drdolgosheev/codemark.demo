package com.codemark.demoapp.controllers;

import com.codemark.demoapp.dto.UserRoleDTO;
import com.codemark.demoapp.model.User;
import com.codemark.demoapp.model.UserRole;
import com.codemark.demoapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Получить пользователей без ролей
    @RequestMapping(value = "/getAllUser", method = RequestMethod.GET)
    public String getAllUsers(){
        List<User> users = userService.getAll();
        return users.toString();
    }

    // Регистрация пользователя
    @RequestMapping(value = "/setNewUser", method = RequestMethod.POST)
    public String  setNewUser(@RequestBody UserRoleDTO userRoleDTO){
        User user = userRoleDTO.getUser();
        String userRole = userRoleDTO.getRole();
        try {
            userService.register(user, userRole);
        }catch (IllegalArgumentException e){
            e.printStackTrace();
        }

        return "New user added\n" + user.toString();
    }

    // Получение пользователя с ролями
    @RequestMapping(value= "/findUserById", method = RequestMethod.GET)
    public String getUser(@RequestBody int userId){
        User user = userService.findById(userId);
        List<UserRole> roles = user.getRoles();
        return user.toString() + "\nRoles: " + roles.toString();
    }

    // Удаление пользователя
    @RequestMapping(value= "/deleteUserById", method = RequestMethod.DELETE)
    public String deleteUser(@RequestBody int userId) {
        userService.delete(userId);
        return "User was deleted";
    }

}
