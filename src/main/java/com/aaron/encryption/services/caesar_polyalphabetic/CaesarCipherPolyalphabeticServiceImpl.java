package com.aaron.encryption.services.caesar_polyalphabetic;

import org.springframework.stereotype.Service;

@Service
public class CaesarCipherPolyalphabeticServiceImpl implements CaesarCipherPolyalphabeticService {
    @Override
    public String encrypt(String text, String key) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (Character.isLetter(ch)) {
                int shift = Character.isUpperCase(key.charAt(i % key.length()))
                        ? key.charAt(i % key.length()) - 'A'
                        : key.charAt(i % key.length()) - 'a';
                result.append(shiftChar(ch, shift));
            } else {
                result.append(ch);
            }
        }
        return result.toString();
    }

    @Override
    public String decrypt(String encryptedText, String key) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < encryptedText.length(); i++) {
            char ch = encryptedText.charAt(i);
            if (Character.isLetter(ch)) {
                int shift = Character.isUpperCase(key.charAt(i % key.length()))
                        ? key.charAt(i % key.length()) - 'A'
                        : key.charAt(i % key.length()) - 'a';
                result.append(shiftChar(ch, -shift));
            } else {
                result.append(ch);
            }
        }
        return result.toString();
    }

    private char shiftChar(char c, int shift) {
        if (Character.isUpperCase(c)) {
            return (char) ((c + shift - 'A' + 26) % 26 + 'A');
        } else if (Character.isLowerCase(c)) {
            return (char) ((c + shift - 'a' + 26) % 26 + 'a');
        } else {
            return c;
        }
    }
}
