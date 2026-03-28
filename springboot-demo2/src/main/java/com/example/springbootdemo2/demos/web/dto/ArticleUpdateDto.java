package com.example.springbootdemo2.demos.web.dto;

import com.example.springbootdemo2.demos.web.anno.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleUpdateDto {
    @NonNull
    private Integer id;          // 文章id
    @NonNull
    private Integer categoryId;  // 文章分类id
    @NotBlank
    @NonNull
    @NotEmpty
    @Size(min = 1, max = 30, message = "标题长度必须在 1-30 之间")
    private String title;       // 文章标题
    @NotBlank
    @NonNull
    @NotEmpty
    private String content;     // 文章正文
    @NotEmpty
    @URL
    private String cover;       // 图片
//    @Status
    private Integer status;      // 状态标记    0-草稿    1-已发布

}
