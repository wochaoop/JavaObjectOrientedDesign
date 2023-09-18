package com.web.D5;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;

public class AESStringEncryptionExample {
    private static final int AES_KEY_SIZE = 256;
    private static final int GCM_IV_SIZE = 12;
    private static final int GCM_TAG_SIZE = 16;

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
    private static SecretKey generateSecretKey() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] keyBytes = new byte[AES_KEY_SIZE / 8];
        secureRandom.nextBytes(keyBytes);
        return new SecretKeySpec(keyBytes, "AES");
    }

    // 加密字符串
    private static String encryptString(String input, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        byte[] iv = new byte[GCM_IV_SIZE];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(iv);
        GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(GCM_TAG_SIZE * 8, iv);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, gcmParameterSpec);
        byte[] encryptedBytes = cipher.doFinal(input.getBytes(StandardCharsets.UTF_8));
        byte[] ivAndEncrypted = new byte[GCM_IV_SIZE + encryptedBytes.length];
        System.arraycopy(iv, 0, ivAndEncrypted, 0, GCM_IV_SIZE);
        System.arraycopy(encryptedBytes, 0, ivAndEncrypted, GCM_IV_SIZE, encryptedBytes.length);
        return Base64.getEncoder().encodeToString(ivAndEncrypted);
    }

    // 解密字符串
    private static String decryptString(String encryptedInput, SecretKey secretKey) throws Exception {
        byte[] ivAndEncrypted = Base64.getDecoder().decode(encryptedInput);
        byte[] iv = new byte[GCM_IV_SIZE];
        byte[] encryptedBytes = new byte[ivAndEncrypted.length - GCM_IV_SIZE];
        System.arraycopy(ivAndEncrypted, 0, iv, 0, GCM_IV_SIZE);
        System.arraycopy(ivAndEncrypted, GCM_IV_SIZE, encryptedBytes, 0, encryptedBytes.length);
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(GCM_TAG_SIZE * 8, iv);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, gcmParameterSpec);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }
}
