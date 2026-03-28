package com.example.springbootdemo2.demos.web.entity;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
public class PageBean<T> {

    private long totalElements;    // 总记录数
    private int totalPages;        // 总页数
    private List<T> content;       // 数据列表

    public PageBean(Page<T> page) {
        this.content = page.getContent();
        this.totalElements = page.getTotalElements();
        this.totalPages = page.getTotalPages();
    }

    // 如果有需要，也可以保留 first、last 等
}
