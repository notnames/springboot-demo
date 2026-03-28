package com.example.springbootdemo2.demos.web.controller;

import com.example.springbootdemo2.demos.web.dto.CategoryDto;
import com.example.springbootdemo2.demos.web.entity.Category1;
import com.example.springbootdemo2.demos.web.entity.PageBean;
import com.example.springbootdemo2.demos.web.entity.Result;
import com.example.springbootdemo2.demos.web.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@Validated
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PostMapping
    public Result add(@RequestBody CategoryDto categoryDto) {
        categoryService.add(categoryDto);
        return Result.success();
    }

    @GetMapping
    public Result<PageBean<Category1>> list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        PageBean<Category1> sc = categoryService.list(page, size);
        return Result.success(sc);
    }

    @GetMapping("/detail")
    public Result<CategoryDto> detail( Integer id) {
        CategoryDto categoryDto = categoryService.detail(id);
        if (categoryDto == null) {
            return Result.error("啥都没有！");
        }
        return Result.success(categoryDto);
    }

    @PutMapping
    public Result update(@RequestBody CategoryDto categoryDto) {
        categoryService.update(categoryDto);
        return Result.success();
    }

    @DeleteMapping
    public Result delete(Integer id) {
        categoryService.delete(id);
        return Result.success();
    }
}
