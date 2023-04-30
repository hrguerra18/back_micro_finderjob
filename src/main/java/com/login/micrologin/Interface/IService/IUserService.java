package com.login.micrologin.Interface.IService;

import com.login.micrologin.Entity.User;
import com.login.micrologin.Service.User.UserResponse;

public interface IUserService {
    UserResponse findUserByEmailAndPassword(String email, String password);
    User findUserByEmail(String email);



}
