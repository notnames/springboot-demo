-- ======================================================
-- 数据库初始化脚本
-- 作者：自动生成
-- 说明：创建数据库 mybatis，以及用户表、文章分类表、文章表。表中字段可以优化，比如说，删除标记字段就不在项目中用到
-- ======================================================

-- 创建数据库 mybatis（如果不存在），设置字符集为 utf8mb4，支持 emoji 和完整 Unicode
CREATE DATABASE IF NOT EXISTS `mybatis` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- 切换到 mybatis 数据库
USE `mybatis`;

-- ======================================================
-- 用户表（user）
-- 说明：存储系统用户的基本信息，包括登录凭证和个人资料
-- ======================================================
CREATE TABLE `user` (
                        `id` INT AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID，自增',
                        `username` VARCHAR(50) NOT NULL UNIQUE COMMENT '用户名，唯一，用于登录',
                        `password` VARCHAR(100) NOT NULL COMMENT '加密后的密码，存储 BCrypt 等加密结果',
                        `nickname` VARCHAR(50) DEFAULT '' COMMENT '昵称，显示名称',
                        `email` VARCHAR(100) DEFAULT '' COMMENT '邮箱，用于找回密码或通知',
                        `avatar` VARCHAR(255) DEFAULT '' COMMENT '头像URL，存储图片地址',
                        `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间，记录用户注册时间',
                        `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间，自动更新'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表，存储用户账号信息';

-- ======================================================
-- 文章分类表（category1）
-- 说明：存储文章的分类，每个分类属于一个用户（创建人）
-- ======================================================
CREATE TABLE `category1` (
                             `id` INT AUTO_INCREMENT PRIMARY KEY COMMENT '分类ID，自增',
                             `username` VARCHAR(50) NOT NULL COMMENT '创建人用户名，关联用户表',
                             `name` VARCHAR(50) NOT NULL COMMENT '分类名称，如“技术”、“生活”',
                             `alias` VARCHAR(50) NOT NULL COMMENT '分类别名，用于 URL 友好，如“tech”、“life”',
                             `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                             `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                             `deleted` TINYINT DEFAULT 0 COMMENT '逻辑删除标记，0-正常，1-已删除'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文章分类表，存储用户自定义的分类';

-- ======================================================
-- 文章表（article1）
-- 说明：存储用户发布的文章，关联作者和分类
-- ======================================================
CREATE TABLE `article1` (
                            `id` INT NOT NULL AUTO_INCREMENT COMMENT '文章ID，自增',
                            `author_id` INT NOT NULL COMMENT '作者ID，关联 user.id',
                            `category_id` INT NOT NULL COMMENT '分类ID，关联 category1.id',
                            `title` VARCHAR(255) COMMENT '文章标题',
                            `content` VARCHAR(255) COMMENT '文章内容（实际生产中应使用 TEXT 类型）',
                            `cover` VARCHAR(255) COMMENT '封面图 URL',
                            `status` INT COMMENT '文章状态：0-草稿，1-已发布，2-审核中等',
                            `deleted` INT DEFAULT 0 COMMENT '逻辑删除标记：0-正常，1-已删除',
                            `created_at` DATETIME(6) COMMENT '创建时间，精确到微秒',
                            `updated_at` DATETIME(6) COMMENT '更新时间，精确到微秒',
                            PRIMARY KEY (`id`),
                            INDEX `i_author_id` (`author_id`) COMMENT '作者索引，加速按作者查询',
                            INDEX `i_category_id` (`category_id`) COMMENT '分类索引，加速按分类查询',
                            CONSTRAINT `fk_article1_author` FOREIGN KEY (`author_id`) REFERENCES `user`(`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
                            CONSTRAINT `fk_article1_category` FOREIGN KEY (`category_id`) REFERENCES `category1`(`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文章表，存储用户发布的文章';
