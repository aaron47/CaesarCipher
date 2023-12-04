package com.aaron.encryption.services.rsa;

import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Base64;

@Service
public class RsaServiceImpl implements RsaService {
    private final Cipher encryptCipher;
    private final Cipher decryptCipher;

    public RsaServiceImpl() throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        this.encryptCipher = Cipher.getInstance("RSA");
        this.decryptCipher = Cipher.getInstance("RSA");

        KeyPair keyPair = generateKeyPair();

        this.encryptCipher.init(Cipher.ENCRYPT_MODE, keyPair.getPublic());
        this.decryptCipher.init(Cipher.DECRYPT_MODE, keyPair.getPrivate());
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

    private KeyPair generateKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        return keyPairGenerator.generateKeyPair();
    }
}
