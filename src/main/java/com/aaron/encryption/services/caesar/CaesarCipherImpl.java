package com.aaron.encryption.services.caesar;

import org.springframework.stereotype.Service;

@Service
public class CaesarCipherImpl implements CaesarCipher {

    @Override
    public String encrypt(String text, int shift) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (Character.isUpperCase(ch)) {
                char c = (char) (((ch + shift - 65) % 26) + 65);
                result.append(c);
            } else if (Character.isLowerCase(ch)) {
                char c = (char) (((ch + shift - 97) % 26) + 97);
                result.append(c);
            } else {
                result.append(ch);
            }
        }
        return result.toString();
    }

    @Override
    public String decrypt(String encryptedText, int shift) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < encryptedText.length(); i++) {
            char ch = encryptedText.charAt(i);
            if (Character.isUpperCase(ch)) {
                char c = (char) (((ch - shift - 65 + 26) % 26) + 65);
                result.append(c);
            } else if (Character.isLowerCase(ch)) {
                char c = (char) (((ch - shift - 97 + 26) % 26) + 97);
                result.append(c);
            } else {
                result.append(ch);
            }
        }
        return result.toString();
    }
}
