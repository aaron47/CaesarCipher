package com.aaron.encryption.services.column_transposition;

public interface ColumnTranspositionService {
    String encrypt(String encryptedText, String key);
    String decrypt(String plainText, String key);
}
