package com.example.springbootdemo2.demos.web.controller;

import com.example.springbootdemo2.demos.web.dto.ArticleCreateDto;
import com.example.springbootdemo2.demos.web.dto.ArticleDetailDto;
import com.example.springbootdemo2.demos.web.dto.ArticleUpdateDto;
import com.example.springbootdemo2.demos.web.dto.CategoryDto;
import com.example.springbootdemo2.demos.web.entity.Article1;
import com.example.springbootdemo2.demos.web.entity.PageBean;
import com.example.springbootdemo2.demos.web.entity.Result;
import com.example.springbootdemo2.demos.web.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    ArticleService articleService;

    @PostMapping("/add")
    public Result add(@RequestBody @Validated  ArticleCreateDto articleCreateDto) {
        articleService.add(articleCreateDto);
        return Result.success();
    }

//    @GetMapping("/list")
    public Result<List<ArticleDetailDto>> list() {
        List<ArticleDetailDto> list = articleService.list();
        if(list.size()>0){
            return Result.success(list);
        }
        return Result.error("空空如也！");
    }

    @GetMapping
    public Result<PageBean<ArticleDetailDto>> list(
            @RequestParam(required = false) Integer status,
            @RequestParam(required = false) Integer categoryId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        PageBean<ArticleDetailDto> pageResult = articleService.findByConditions(status, categoryId, page, size);
        return Result.success(pageResult);
    }

    @GetMapping("/detail")
    public Result<ArticleDetailDto> detail(Integer id) {
        ArticleDetailDto Article = articleService.detail(id);
        if(Article==null){
            return Result.error("空空如也！请先添加内容！");
        }
        return Result.success(Article);
    }

    @PutMapping
    public Result update(@RequestBody @Validated ArticleUpdateDto article) {
        articleService.update(article);
        return Result.success();
    }

    @DeleteMapping
    public Result delete(Integer id) {
        articleService.delete(id);
        return Result.success();
    }
}
