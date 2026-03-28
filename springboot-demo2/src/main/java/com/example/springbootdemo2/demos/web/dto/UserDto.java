package com.example.springbootdemo2.demos.web.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class UserDto {
    // 1. 非空校验 (null, "", "   " 都会失败)
    @NotBlank(message = "用户名不能为空")
    // 2. 长度校验 (4 到 20 个字符)
    @Size(min = 4, max = 20, message = "用户名长度必须在 4-20 之间")
    private String username;

    // 1. 非空校验
    @NotBlank(message = "密码不能为空")
    // 2. 长度校验 (6 到 30 个字符)
    @Size(min = 6, max = 30, message = "密码长度必须在 6-30 之间")
    private String password;
}
