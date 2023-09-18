package com.web.D5;

import org.bouncycastle.crypto.generators.Argon2BytesGenerator;
import org.bouncycastle.crypto.params.Argon2Parameters;

import java.security.SecureRandom;
import java.util.Base64;

public class PasswordSecurityExample {
    public static void main(String[] args) {
        // 用户注册时设置密码
        String password = "123456";

        // 生成密码哈希
        String hashedPassword = hashPassword(password);

        System.out.println("原始密码: " + password);
        System.out.println("存储的哈希密码: " + hashedPassword);

        // 用户登录时验证密码
        String enteredPassword = "123456";

        // 验证密码
        boolean passwordMatch = checkPassword(enteredPassword, hashedPassword);

        if (passwordMatch) {
            System.out.println("密码匹配，登录成功！");
        } else {
            System.out.println("密码不匹配，登录失败！");
        }
    }

    // 生成密码哈希
    private static String hashPassword(String password) {
        // 创建随机盐值
        byte[] salt = generateSalt();

        // 配置Argon2参数
        Argon2Parameters parameters = configureArgon2Parameters(salt);

        // 计算哈希值
        byte[] hash = calculateArgon2Hash(password, parameters);

        // 返回哈希值和盐值的Base64编码
        String saltBase64 = Base64.getEncoder().encodeToString(salt);
        String hashBase64 = Base64.getEncoder().encodeToString(hash);

        return saltBase64 + "$" + hashBase64;
    }

    // 验证用户输入的密码是否与存储的哈希密码匹配
    private static boolean checkPassword(String enteredPassword, String storedHashedPassword) {
        // 从存储的哈希密码中提取盐值
        String[] parts = storedHashedPassword.split("\\$");
        if (parts.length != 2) {
            return false; // 无效的哈希密码格式
        }

        byte[] salt = Base64.getDecoder().decode(parts[0]);

        // 配置Argon2参数
        Argon2Parameters parameters = configureArgon2Parameters(salt);

        // 计算用户输入密码的哈希值
        byte[] hash = calculateArgon2Hash(enteredPassword, parameters);

        // 将用户输入密码的哈希值与存储的哈希值进行比较
        String enteredPasswordHash = Base64.getEncoder().encodeToString(hash);

        return enteredPasswordHash.equals(parts[1]);
    }

    // 生成随机的盐值
    private static byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[32]; // 盐值长度可以根据需要调整
        random.nextBytes(salt);
        return salt;
    }

    // 配置Argon2参数
    private static Argon2Parameters configureArgon2Parameters(byte[] salt) {
        return new Argon2Parameters.Builder(Argon2Parameters.ARGON2_id)
                .withVersion(Argon2Parameters.ARGON2_VERSION_13)
                .withMemoryAsKB(65536) // 内存成本，根据需要调整
                .withIterations(4) // 迭代次数，根据需要调整
                .withSalt(salt)
                .build();
    }

    // 计算Argon2哈希
    private static byte[] calculateArgon2Hash(String password, Argon2Parameters parameters) {
        Argon2BytesGenerator generator = new Argon2BytesGenerator();
        generator.init(parameters);
        byte[] hash = new byte[64]; // 输出长度为64字节
        generator.generateBytes(password.getBytes(), hash);
        return hash;
    }
}
