package com.example.springbootdemo2.demos.web.dto;

import com.example.springbootdemo2.demos.web.anno.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleCreateDto {


    @NonNull
    private Integer categoryId;  // 文章分类id
    @NotBlank
    @NonNull
    @NotEmpty
    private String title;       // 文章标题
    @NotBlank
    private String content;     // 文章正文
    @NotBlank
    @URL
    private String cover;       // 图片
    @NonNull
    private Integer status;      // 状态标记    0-草稿    1-已发布

}
