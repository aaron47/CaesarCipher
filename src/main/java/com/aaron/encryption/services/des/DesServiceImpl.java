package com.aaron.encryption.services.des;

import org.springframework.stereotype.Service;

import javax.crypto.*;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Service
public class DesServiceImpl implements DesService {
    private final Cipher encryptCipher;

    private final Cipher decryptCipher;

    public DesServiceImpl() throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        encryptCipher = Cipher.getInstance("DES");
        decryptCipher = Cipher.getInstance("DES");
        SecretKey secretKey = KeyGenerator.getInstance("DES").generateKey();
        encryptCipher.init(Cipher.ENCRYPT_MODE, secretKey);
        decryptCipher.init(Cipher.DECRYPT_MODE, secretKey);
    }

    @Override
    public String encrypt(String str) throws Exception {
        byte[] utf8 = str.getBytes(StandardCharsets.UTF_8);
        byte[] enc = this.encryptCipher.doFinal(utf8);
        return Base64.getEncoder().encodeToString(enc);
    }

    @Override
    public String decrypt(String str) throws Exception {
        byte[] dec = Base64.getDecoder().decode(str);
        byte[] utf8 = this.decryptCipher.doFinal(dec);
        return new String(utf8, StandardCharsets.UTF_8);
    }
}
