package com.example.springbootdemo2.demos.web.dto;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class ArticleDetailDto {
    private Integer id;          // 文章id
    private Integer authorId;    // 用户id
    private Integer categoryId;  // 文章分类id

    private String title;       // 文章标题
    private String content;     // 文章正文
    private String cover;       // 图片

    private Integer status;      // 状态标记    0-草稿    1-已发布
    private Integer deleted;     // 删除标记    未使用字段

    private LocalDateTime createdAt;   // 创建时间
    private LocalDateTime updatedAt;   // 修改时间
}
