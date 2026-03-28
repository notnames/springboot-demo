package com.example.springbootdemo2.demos.web.service.impl;

import com.example.springbootdemo2.demos.web.context.UserContext;
import com.example.springbootdemo2.demos.web.dto.ArticleCreateDto;
import com.example.springbootdemo2.demos.web.dto.ArticleDetailDto;
import com.example.springbootdemo2.demos.web.dto.ArticleUpdateDto;
import com.example.springbootdemo2.demos.web.entity.Article1;
import com.example.springbootdemo2.demos.web.entity.PageBean;
import com.example.springbootdemo2.demos.web.entity.User;
import com.example.springbootdemo2.demos.web.mapper.Article1Repository;
import com.example.springbootdemo2.demos.web.mapper.UserRepository;
import com.example.springbootdemo2.demos.web.service.ArticleService;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotBlank;
import java.net.ContentHandler;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    Article1Repository article1Repository;
    @Autowired
    UserRepository userRepository;

    @Override
    public void add(ArticleCreateDto articleCreateDto) {
        String username = UserContext.getCurrentUser();
        User user = userRepository.findByUsername(username);
        // 创建实体类
        Article1 article = new Article1();
        article.setAuthorId(user.getId());
        article.setCategoryId(articleCreateDto.getCategoryId());
        article.setTitle(articleCreateDto.getTitle());
        article.setContent(articleCreateDto.getContent());
        article.setCover(articleCreateDto.getCover());
        article.setStatus(articleCreateDto.getStatus());
        article1Repository.save(article);
    }

    @Override
    public List<ArticleDetailDto> list() {
        String username = UserContext.getCurrentUser();
        User user = userRepository.findByUsername(username);
        return article1Repository.findByAuthorId(user.getId());
    }

    @Override
    public PageBean<ArticleDetailDto> findByConditions(Integer status, Integer categoryId, int page, int size) {
        String username = UserContext.getCurrentUser();
        User user = userRepository.findByUsername(username);

        // 构建动态条件
        Specification<ArticleDetailDto> spec = Specification.where(
                (root, query, cb) ->
                        cb.equal(root.get("authorId"), user.getId()));  // 只能查看自己的文章);


        if (status != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("status"), status));
        }
        if (categoryId != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("categoryId"), categoryId));
        }

        // 分页参数（页号从0开始，也可传入Sort排序）
        Pageable pageable = PageRequest.of(page, size);
        Page<ArticleDetailDto> all = article1Repository.findAll(spec, pageable);

        return new PageBean<>(all);

    }

    @Override
    public ArticleDetailDto detail(Integer id) {
        String username = UserContext.getCurrentUser();
        User user = userRepository.findByUsername(username);
        Article1 byId = article1Repository.findById(id)
                .orElseThrow(() -> new RuntimeException("内容不存在!"));
        if (!user.getId().equals(byId.getAuthorId())) {
            throw new RuntimeException("无操作权限！");
        }
        ArticleDetailDto dto = new ArticleDetailDto();
        dto.setId(byId.getId());
        dto.setAuthorId(byId.getAuthorId());
        dto.setCategoryId(byId.getCategoryId());
        dto.setTitle(byId.getTitle());
        dto.setContent(byId.getContent());
        dto.setCover(byId.getCover());
        dto.setStatus(byId.getStatus());
        dto.setDeleted(byId.getDeleted());
        dto.setCreatedAt(byId.getCreatedAt());
        dto.setUpdatedAt(byId.getUpdatedAt());
        return dto;
    }


    @Override
    public void update(ArticleUpdateDto article) {
        String username = UserContext.getCurrentUser();
        User user = userRepository.findByUsername(username);
        Article1 repositoryById = article1Repository.getById(article.getId());
        if (!user.getId().equals(repositoryById.getAuthorId())) {
            throw new RuntimeException("无操作权限!");
        }

        repositoryById.setCategoryId(article.getCategoryId());
        repositoryById.setTitle(article.getTitle());
        repositoryById.setContent(article.getContent());
        repositoryById.setCover(article.getCover());
        repositoryById.setStatus(article.getStatus());
        article1Repository.save(repositoryById);
    }

    @Override
    public void delete(Integer id) {
        String username = UserContext.getCurrentUser();
        User user = userRepository.findByUsername(username);
        Article1 repositoryById = article1Repository.findById(id)
                .orElseThrow(() -> new RuntimeException("内容不存在!"));
        if (!user.getId().equals(repositoryById.getAuthorId())) {
            throw new RuntimeException("无操作权限!");
        }
        article1Repository.deleteById(id);
    }

}
