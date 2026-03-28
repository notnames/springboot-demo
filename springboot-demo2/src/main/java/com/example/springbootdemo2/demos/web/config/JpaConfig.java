package com.example.springbootdemo2.demos.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@Configuration
@EnableJpaAuditing  // 关键：启用 JPA 审计功能
public class JpaConfig {
}
