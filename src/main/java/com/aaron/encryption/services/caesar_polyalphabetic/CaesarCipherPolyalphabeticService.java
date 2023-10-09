package com.aaron.encryption.services.caesar_polyalphabetic;

public interface CaesarCipherPolyalphabeticService {
    String encrypt(String text, String key);
    String decrypt(String text, String key);
}
