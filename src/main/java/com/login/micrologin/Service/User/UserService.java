package com.login.micrologin.Service.User;

import com.login.micrologin.Entity.User;
import com.login.micrologin.Interface.IService.IUserService;
import com.login.micrologin.Repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {
    private  UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResponse findUserByEmailAndPassword(String email, String password) {
        UserResponse response = new UserResponse();



        User user = userRepository.findUserByEmailAndPassword(email, password);
        if (user == null){
            response.messagge = "No existe un usuario con el email "+ email;
            return response;
        }
        response.user = user;
        response.user.setCompany(null);
        return response;
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }
}
