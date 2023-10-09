package com.aaron.encryption.services.caesar;

public interface CaesarCipher {
    String encrypt(String text, int shift);

    String decrypt(String encryptedText, int shift);
}
