package com.example.springbootdemo2.demos.web.anno;

import com.example.springbootdemo2.demos.web.validation.StatusValidation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {StatusValidation.class})
public @interface Status {
    // 校验失败后的提示信息
    String message() default "status值只能是0或1，0代表草稿，1代表已发布";

    //分组
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
