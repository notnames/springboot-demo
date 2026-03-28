package com.example.springbootdemo2.demos.web.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "category1")
@NoArgsConstructor
@AllArgsConstructor
public class Category1 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String name;
    private String alias;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Integer deleted;


    @PrePersist
    @Column(name = "created_at", updatable = false)
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        deleted = 0;
    }

    @PreUpdate
    @Column(name = "updated_at")
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
