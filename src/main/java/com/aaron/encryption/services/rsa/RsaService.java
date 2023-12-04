package com.aaron.encryption.services.rsa;


public interface RsaService {
   String encrypt(String str) throws Exception;

    String decrypt(String str) throws Exception;
}
