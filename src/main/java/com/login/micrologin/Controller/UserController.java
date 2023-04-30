package com.login.micrologin.Controller;

import com.login.micrologin.Entity.User;
import com.login.micrologin.Interface.IService.IUserService;
import com.login.micrologin.Service.User.UserResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/findUserByEmailAndPassword")
    public UserResponse findUserByEmailAndPassword(@RequestBody User user){
        return userService.findUserByEmailAndPassword(user.getEmail(), user.getPassword());
    }
}
