package com.example.springbootdemo2.demos.web.mapper;

import com.example.springbootdemo2.demos.web.dto.ArticleDetailDto;
import com.example.springbootdemo2.demos.web.entity.Category1;
import com.example.springbootdemo2.demos.web.entity.PageBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Category1Repository extends JpaRepository<Category1,Integer>, JpaSpecificationExecutor<Category1> {
//    Page<Category1> findByUsername(String username);

    Page<Category1> findAll(Specification<Category1> spec, Pageable pageable);
}
