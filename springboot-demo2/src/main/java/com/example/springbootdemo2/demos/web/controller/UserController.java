package com.example.springbootdemo2.demos.web.controller;


import com.example.springbootdemo2.demos.web.dto.*;
import com.example.springbootdemo2.demos.web.entity.Result;

import com.example.springbootdemo2.demos.web.service.UserService;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserController {
    // 注册

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public Result register(@RequestBody @Validated UserDto userDto) {
        return userService.register(userDto);
    }

    @PostMapping("/login")
    public Result login(@RequestBody @Validated UserDto userDto) {
        return userService.login(userDto);
    }

    @GetMapping("/userinfo")
    public Result<UserInfoDto> userInfo() {
        UserInfoDto user = userService.userInfo();
//        System.out.println("userInfo被调用" +  user);
        return Result.success(user);
    }

    @PutMapping("/update")
    public Result update(@RequestBody @Validated UpdateUserInfoDto userDto) {
        return userService.update(userDto);
    }

    @PatchMapping("/updateAvatar")
    public Result updateAvatar(@RequestParam @URL @Validated String avatarUrl) {
        userService.updateAvatar(avatarUrl);
        return Result.success();
    }

    @PatchMapping("/updatePwd")
    public Result updatePwd(@RequestBody @Validated PasswordDto passwordDto) {
        userService.updatePwd(passwordDto);
        return Result.success();
    }

}
