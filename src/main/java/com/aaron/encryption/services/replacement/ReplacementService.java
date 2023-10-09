package com.aaron.encryption.services.replacement;

public interface ReplacementService {
    String encrypt(String text);
    String decrypt(String encryptedText);
}
