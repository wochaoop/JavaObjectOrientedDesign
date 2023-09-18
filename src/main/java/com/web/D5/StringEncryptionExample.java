package com.web.D5;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;

public class StringEncryptionExample {
    public static void main(String[] args) throws Exception {
        // 原始字符串
        String originalString = "这是一个需要加密的字符串";

        // 生成随机密钥
        SecretKey secretKey = generateSecretKey();

        // 加密字符串
        String encryptedString = encryptString(originalString, secretKey);

        System.out.println("原始字符串: " + originalString);
        System.out.println("加密后的字符串: " + encryptedString);

        // 解密字符串
        String decryptedString = decryptString(encryptedString, secretKey);

        System.out.println("解密后的字符串: " + decryptedString);
    }

    // 生成随机密钥
    private static SecretKey generateSecretKey() throws Exception {
        // 使用SHA-256生成密钥的字节数组
        String password = "密钥密码"; // 可以使用更强的方式来生成密码
        MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
        byte[] keyBytes = sha256.digest(password.getBytes(StandardCharsets.UTF_8));

        // 使用前16个字节作为密钥
        keyBytes = Arrays.copyOf(keyBytes, 16);

        // 创建SecretKey对象
        return new SecretKeySpec(keyBytes, "AES");
    }

    // 加密字符串
    private static String encryptString(String input, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(input.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    // 解密字符串
    private static String decryptString(String encryptedInput, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedInput);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }
}