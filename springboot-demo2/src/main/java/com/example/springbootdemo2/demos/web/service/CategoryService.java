package com.example.springbootdemo2.demos.web.service;

import com.example.springbootdemo2.demos.web.dto.CategoryDto;
import com.example.springbootdemo2.demos.web.entity.Category1;
import com.example.springbootdemo2.demos.web.entity.PageBean;

import javax.validation.Valid;
import java.util.List;

public interface CategoryService {
    void add(@Valid CategoryDto categoryDto);

    PageBean<Category1> list(int page,  int size);

    CategoryDto detail(Integer id);

    void update(@Valid CategoryDto categoryDto);

    void delete(Integer id);
}
