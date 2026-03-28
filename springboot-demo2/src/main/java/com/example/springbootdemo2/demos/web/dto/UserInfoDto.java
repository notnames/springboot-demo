package com.example.springbootdemo2.demos.web.dto;


import lombok.Data;
import java.time.LocalDateTime;

@Data
public class UserInfoDto {

    private Integer id;
    private String username;
    private String nickname;
    private String email;
    private String avatar;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
