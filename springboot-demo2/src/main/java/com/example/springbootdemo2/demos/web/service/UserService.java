package com.example.springbootdemo2.demos.web.service;

import com.example.springbootdemo2.demos.web.dto.*;
import com.example.springbootdemo2.demos.web.entity.Result;
import com.example.springbootdemo2.demos.web.entity.User;

import javax.validation.Valid;


public interface UserService {
    Result register(UserDto userDto);

    Result login(UserDto userDto);

    UserInfoDto userInfo();

    Result update(UpdateUserInfoDto userDto);

    void updateAvatar(String avatarUrl);

    void updatePwd(@Valid PasswordDto passwordDto);
}
