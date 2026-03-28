package com.example.springbootdemo2.demos.web.service.impl;

import com.example.springbootdemo2.demos.web.context.UserContext;
import com.example.springbootdemo2.demos.web.dto.ArticleDetailDto;
import com.example.springbootdemo2.demos.web.dto.CategoryDto;
import com.example.springbootdemo2.demos.web.entity.Article1;
import com.example.springbootdemo2.demos.web.entity.Category1;
import com.example.springbootdemo2.demos.web.entity.PageBean;
import com.example.springbootdemo2.demos.web.entity.User;
import com.example.springbootdemo2.demos.web.mapper.Category1Repository;
import com.example.springbootdemo2.demos.web.mapper.UserRepository;
import com.example.springbootdemo2.demos.web.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    Category1Repository category1Repository;

    @Autowired
    UserRepository userRepository;

    @Override
    public void add(CategoryDto categoryDto) {
        String username = UserContext.getCurrentUser();
        Category1 category = new Category1();
        category.setUsername(username);
        category.setName(categoryDto.getName());
        category.setAlias(categoryDto.getAlias());
        category1Repository.save(category);
    }

    @Override
    public PageBean<Category1> list(int page, int size) {
        String username = UserContext.getCurrentUser();
        // 构建动态条件
        Specification<Category1> spec = Specification.where(
                (root, query, cb) ->
                        cb.equal(root.get("username"), username));

        // 分页参数（页号从0开始，也可传入Sort排序）
        Pageable pageable = PageRequest.of(page, size);
        Page<Category1> byUsername = category1Repository.findAll(spec, pageable);


//        Page<Category1> byUsername = category1Repository.findByUsername(username);
        return new PageBean<>(byUsername);
    }

    @Override
    public CategoryDto detail(Integer id) {
        String username = UserContext.getCurrentUser();
        Category1 category = category1Repository.findById(id)
                .orElseThrow(() -> new RuntimeException("分类不存在，ID：" + id));

        if (!category.getUsername().equals(username)) {
            throw new RuntimeException("无操作权限！");
        }

        CategoryDto dto = new CategoryDto();
        dto.setId(category.getId());
        dto.setName(category.getName());
        dto.setAlias(category.getAlias());
        return dto;
    }

    @Override
    public void update(CategoryDto categoryDto) {
        String username = UserContext.getCurrentUser();
        Category1 category = category1Repository.findById(categoryDto.getId())
                .orElseThrow(() -> new RuntimeException("分类不存在"));
        if (!category.getUsername().equals(username)) {
            throw new RuntimeException("无操作权限！");
        }
        category.setName(categoryDto.getName());
        category.setAlias(categoryDto.getAlias());
        category1Repository.save(category);
    }

    @Override
    public void delete(Integer id) {
        String username = UserContext.getCurrentUser();
        Category1 category = category1Repository.findById(id)
                .orElseThrow(() -> new RuntimeException("分类不存在"));
        if (!category.getUsername().equals(username)) {
            throw new RuntimeException("无操作权限！");
        }
        category1Repository.deleteById(id);
    }
}
