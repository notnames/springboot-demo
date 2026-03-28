package com.example.springbootdemo2.demos.web.service.impl;

import com.example.springbootdemo2.demos.web.context.UserContext;
import com.example.springbootdemo2.demos.web.dto.*;
import com.example.springbootdemo2.demos.web.entity.Result;
import com.example.springbootdemo2.demos.web.entity.User;
import com.example.springbootdemo2.demos.web.mapper.UserRepository;
import com.example.springbootdemo2.demos.web.service.UserService;
import com.example.springbootdemo2.demos.web.util.JwtUtil;
import com.example.springbootdemo2.demos.web.util.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @Override
    public Result register(UserDto userDto) {

        // 验证账号是否被占用
        if (userRepository.findByUsername(userDto.getUsername()) != null) {
            return Result.error("该账号已被占用！");
        }
        // 密码加密处理
        String encode = PasswordUtils.encode(userDto.getPassword());
        // 创建实体对象
        User u = new User();
        u.setUsername(userDto.getUsername());
        u.setPassword(encode);
        // 注册
        userRepository.save(u);
        return Result.success("注册成功！");
    }

    @Override
    public Result login(UserDto userDto) {
        // 验证是否存在账户
        User user = userRepository.findByUsername(userDto.getUsername());
        if (user == null) {
            return Result.error("账户不存在！");
        }
        // 密码是否正确
        if (PasswordUtils.matches(userDto.getPassword(), user.getPassword())) {
            String token = JwtUtil.generateToken(userDto.getUsername());
            String tokenName = "token-" + JwtUtil.getUsernameFromToken(token);
            stringRedisTemplate.opsForValue().set(tokenName, token, 1, TimeUnit.HOURS);
            return Result.success(token);
        }
        return Result.error("密码错误！");
    }

    @Override
    public UserInfoDto userInfo() {
        String username = UserContext.getCurrentUser();
        User user = userRepository.findByUsername(username);
        UserInfoDto userInfoDto = new UserInfoDto();
        userInfoDto.setId(user.getId());
        userInfoDto.setUsername(user.getUsername());
        userInfoDto.setNickname(user.getNickname());
        userInfoDto.setAvatar(user.getAvatar());
        userInfoDto.setEmail(user.getEmail());
        userInfoDto.setCreatedAt(user.getCreatedAt());
        userInfoDto.setUpdatedAt(user.getUpdatedAt());
        return userInfoDto;
    }

    @Override
    public Result update(UpdateUserInfoDto userDto) {
        String username = UserContext.getCurrentUser();
        User user = userRepository.findByUsername(username);
        if (user == null) {
            return Result.error("用户不存在");
        }
        if (StringUtils.hasText(userDto.getNickname())) {
            user.setNickname(userDto.getNickname());
        }
        if (StringUtils.hasText(userDto.getEmail())) {
            user.setEmail(userDto.getEmail());
        }
        userRepository.save(user);
        return Result.success("已更新！");
    }

    @Override
    public void updateAvatar(String avatarUrl) {
        String username = UserContext.getCurrentUser();
        User user = userRepository.findByUsername(username);
        user.setAvatar(avatarUrl);
        userRepository.save(user);
    }

    @Override
    public void updatePwd(PasswordDto passwordDto) {
        String username = UserContext.getCurrentUser();
        User user = userRepository.findByUsername(username);
        if (!PasswordUtils.matches(passwordDto.getOldPassword(), user.getPassword())) {
            throw  new RuntimeException("旧密码错误");
        }
        if (!passwordDto.getNewPassword().equals(passwordDto.getConfirmNewPassword())) {
            throw  new RuntimeException("新密码不一致");
        }

        // 密码加密处理
        String encode = PasswordUtils.encode(passwordDto.getNewPassword());
        user.setPassword(encode);
        userRepository.save(user);
        String tokenName = "token-" + username;
        stringRedisTemplate.delete(tokenName);
    }


}
