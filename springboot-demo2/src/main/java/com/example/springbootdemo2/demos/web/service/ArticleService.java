package com.example.springbootdemo2.demos.web.service;

import com.example.springbootdemo2.demos.web.dto.ArticleCreateDto;
import com.example.springbootdemo2.demos.web.dto.ArticleDetailDto;
import com.example.springbootdemo2.demos.web.dto.ArticleUpdateDto;
import com.example.springbootdemo2.demos.web.entity.Article1;
import com.example.springbootdemo2.demos.web.entity.PageBean;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ArticleService {
    void add(ArticleCreateDto articleCreateDto);

    List<ArticleDetailDto> list();

    ArticleDetailDto detail(Integer id);

    void update(ArticleUpdateDto article);

    void delete(Integer id);

    PageBean<ArticleDetailDto> findByConditions(Integer status, Integer categoryId, int page, int size);
}