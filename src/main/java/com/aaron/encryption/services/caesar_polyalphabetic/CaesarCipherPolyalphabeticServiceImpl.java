package com.aaron.encryption.services.caesar_polyalphabetic;

import org.springframework.stereotype.Service;

@Service
public class CaesarCipherPolyalphabeticServiceImpl implements CaesarCipherPolyalphabeticService {

    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final int ALPHABET_SIZE = ALPHABET.length();

    @Override
    public String encrypt(String text, String key) {
        StringBuilder encryptedText = new StringBuilder();
        for (int i = 0, j = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (Character.isLetter(c)) {
                c = Character.toUpperCase(c);
                encryptedText.append(encryptChar(c, key.charAt(j)));
                j = ++j % key.length();
            } else {
                encryptedText.append(c);
            }
        }
        return encryptedText.toString();
    }

    @Override
    public String decrypt(String encryptedText, String key) {
        StringBuilder decryptedText = new StringBuilder();
        for (int i = 0, j = 0; i < encryptedText.length(); i++) {
            char c = encryptedText.charAt(i);
            if (Character.isLetter(c)) {
                c = Character.toUpperCase(c);
                decryptedText.append(decryptChar(c, key.charAt(j)));
                j = ++j % key.length();
            } else {
                decryptedText.append(c);
            }
        }
        return decryptedText.toString();
    }

    // Helper function to encrypt a single character
    private char encryptChar(char c, char keyChar) {
        int row = ALPHABET.indexOf(Character.toUpperCase(keyChar));
        int col = ALPHABET.indexOf(c);
        return ALPHABET.charAt((row + col) % 26);
    }

    private char decryptChar(char c, char keyChar) {
        int row = ALPHABET.indexOf(Character.toUpperCase(keyChar));
        int col = ALPHABET.indexOf(c);
        return ALPHABET.charAt((col - row + 26) % 26);
    }
}
