package com.example.springbootdemo2.demos.web.util;

import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 * 密码加密工具类 (基于 BCrypt 算法)
 *
 * 【核心特性】:
 * 1. 安全性高：自动加盐，防止彩虹表攻击。
 * 2. 不可逆：无法通过密文反推明文密码。
 * 3. 验证机制：通过 matches 方法比对明文和密文。
 *
 * 【使用流程】:
 * 1. 用户注册/修改密码: 调用 encode() 生成密文，存入数据库。
 * 2. 用户登录: 调用 matches() 比对输入密码和数据库密文。
 */
public class PasswordUtils {

    // BCrypt 的强度因子 (4-31)，默认 10。数值越大越安全但越慢。
    // 10 是目前推荐的平衡值，耗时约 100ms 左右，足以阻挡暴力破解。
    private static final int STRENGTH = 10;

    /**
     * 对明文密码进行加密 (用于注册或修改密码)
     *
     * @param rawPassword 用户输入的明文密码
     * @return 加密后的密文 (存入数据库)
     *
     * 示例:
     * 输入: "123456"
     * 输出: "$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy"
     *       (注意：每次运行结果都不同，因为盐是随机的，这是正常的！)
     */
    public static String encode(String rawPassword) {
        if (rawPassword == null || rawPassword.isEmpty()) {
            throw new IllegalArgumentException("密码不能为空");
        }
        // BCrypt.hashpw 会自动生成随机盐并嵌入到结果字符串中
        return BCrypt.hashpw(rawPassword, BCrypt.gensalt(STRENGTH));
    }

    /**
     * 验证密码是否正确 (用于登录)
     *
     * @param rawPassword     用户登录时输入的明文密码
     * @param encodedPassword 数据库中存储的密文
     * @return true: 密码正确; false: 密码错误
     *
     * 原理:
     * 从 encodedPassword 中提取出之前生成的盐，用同样的盐对 rawPassword 进行加密，
     * 然后比较结果是否一致。
     */
    public static boolean matches(String rawPassword, String encodedPassword) {
        if (rawPassword == null || encodedPassword == null) {
            return false;
        }
        try {
            return BCrypt.checkpw(rawPassword, encodedPassword);
        } catch (IllegalArgumentException e) {
            // 如果数据库中的密文格式不对，返回 false
            return false;
        }
    }

    // ================= 测试 Main 方法 =================
    public static void main(String[] args) {
        String originalPwd = "mySecretPassword123";

        System.out.println("=== 密码加密演示 ===");
        // 1. 加密 (模拟注册)
        String encrypted1 = encode(originalPwd);
        String encrypted2 = encode(originalPwd); // 再次加密，结果会不同！

        System.out.println("原始密码: " + originalPwd);
        System.out.println("加密结果1: " + encrypted1);
        System.out.println("加密结果2: " + encrypted2);
        System.out.println("两次加密结果是否相同？ " + encrypted1.equals(encrypted2));
        // 输出: false (因为盐不同，这是安全的体现)

        System.out.println("\n=== 密码验证演示 ===");
        // 2. 验证 (模拟登录)
        boolean success1 = matches(originalPwd, encrypted1);
        boolean success2 = matches("wrongPassword", encrypted1);
        boolean success3 = matches(originalPwd, encrypted2);

        System.out.println("验证密码(正确): " + success1); // true
        System.out.println("验证密码(错误): " + success2); // false
        System.out.println("验证密码(用另一个密文): " + success3); // true (虽然密文不同，但都是对同一个明文的正确加密)
    }
}