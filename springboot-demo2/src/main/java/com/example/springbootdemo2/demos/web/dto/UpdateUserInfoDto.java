package com.example.springbootdemo2.demos.web.dto;

import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
public class UpdateUserInfoDto {

    @Size(min = 1, max = 20, message = "用户名长度必须在 1-20 之间")
    private String nickname;
    @Email
    private String email;
}
