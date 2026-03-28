package com.example.springbootdemo2.demos.web.interceptor;

import com.example.springbootdemo2.demos.web.context.UserContext;
import com.example.springbootdemo2.demos.web.entity.Result;
import com.example.springbootdemo2.demos.web.exception.UnauthorizedException;
import com.example.springbootdemo2.demos.web.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 这是一个拦截器，用于执行方法之前，验证token是否异常
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 从 request 中获取 Token
        String token = request.getHeader("Authorization");

        if (token == null || token.isEmpty()) {
            throw new UnauthorizedException("token 未提供，请先登录");
        }
        if (!JwtUtil.validateToken(token)) {
            throw new UnauthorizedException("token 无效或已过期");
        }
        String username = JwtUtil.getUsernameFromToken(token);
        String tokenName = "token-" + username;
        String string = stringRedisTemplate.opsForValue().get(tokenName);
        if (string == null || !string.equals(token)) {
            throw new UnauthorizedException("token 已过期，请重新登录！");
        }
        UserContext.setCurrentUser(username);
        return true;
    }

//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
//        // 请求结束后清除，防止内存泄漏
//        UserContext.clear();
//    }
}
