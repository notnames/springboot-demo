package com.example.springbootdemo2.demos.web.dto;

import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotEmpty;

@Data
public class AvatarUrl {
    @NotEmpty
    @URL
    private String avatar;
}
