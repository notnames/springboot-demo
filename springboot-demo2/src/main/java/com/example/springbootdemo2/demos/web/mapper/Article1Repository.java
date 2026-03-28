package com.example.springbootdemo2.demos.web.mapper;

import com.example.springbootdemo2.demos.web.dto.ArticleDetailDto;
import com.example.springbootdemo2.demos.web.entity.Article1;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Article1Repository extends JpaRepository<Article1,Integer>, JpaSpecificationExecutor<ArticleDetailDto> {

    List<ArticleDetailDto> findByAuthorId(Integer id);

    Page<ArticleDetailDto> findAll(Specification<ArticleDetailDto> spec, Pageable pageable);
}
