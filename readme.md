-- jar目录提供了直接打包的jar包，和sql以及redis的配置文件，和本地的设置存在冲突可以直接修改。

-- springboot目录下，提供了源码。本项目只有后端，参考"黑马程序员"的《大事件》项目，经过个人的一些改造，制作而成。在图片保存功能这里未实现，仅仅做了url参数校验。

-- 使用sql目录下的sql文件可以创建对应数据库

-- 提供了可运行的redis，运行方式自行查找攻略

-- apipost目录是个人在apipoet测试controller接口时使用的，可直接导入apipost应用进行接口测试。

    --可能需要设置环境，在apipost中，设置前置url：localhost:8080
    --全局参数：Header：
        Authorization：String：eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJrZXFpbmciLCJpYXQiOjE3NzQ2MDkxMTksImV4cCI6MTc3NDYxMjcxOX0.5Uvc5RWV52VOZ5pLUQwUC_j3qQRx5a77uhMD0YP_gEI
            --这里的参数值的一长串字符串，是使用登录接口生成的token，将内容复制后替换即可，
            --否则将除了注册和登录接口，都会被拦截器拦截，报token异常。
