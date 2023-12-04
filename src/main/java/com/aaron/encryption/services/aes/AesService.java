package com.aaron.encryption.services.aes;


public interface AesService {
     String encrypt(String str) throws Exception;

    String decrypt(String str) throws Exception;
}
