package com.example.springbootdemo2.demos.web.dto;

import com.example.springbootdemo2.demos.web.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
//@NoArgsConstructor
//@AllArgsConstructor
public class CategoryDto {

    private Integer id;

    // 1. 非空校验
    @NotBlank(message = "分类名称不能为空")
    // 2. 长度校验 (1 到 30 个字符)
    @Size(min = 1, max = 30, message = "分类名称长度必须在 1-30 之间")
    private String name;    // 分类名称

    // 2. 长度校验 (30 个字符)
    @Size(max = 30, message = "别名长度不能超过30")
    private String alias;   // 分类别名
}
