package com.example.springbootdemo2.demos.web.mapper;

import com.example.springbootdemo2.demos.web.dto.UpdateUserInfoDto;
import com.example.springbootdemo2.demos.web.dto.UserInfoDto;
import com.example.springbootdemo2.demos.web.entity.User;
import org.apache.ibatis.annotations.Update;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    User findByUsername(String username);

}
