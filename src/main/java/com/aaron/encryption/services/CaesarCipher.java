package com.aaron.encryption.services;

public interface CaesarCipher {
    String encrypt(String text, int shift);

    String decrypt(String encryptedText, int shift);
}
