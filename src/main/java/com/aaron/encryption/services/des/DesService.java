package com.aaron.encryption.services.des;


public interface DesService {
    String encrypt(String str) throws Exception;

    String decrypt(String str) throws Exception;
}
