package com.aaron.encryption.services.caesar_polyalphabetic;

import org.springframework.stereotype.Service;

@Service
public class CaesarCipherPolyalphabeticServiceImpl implements CaesarCipherPolyalphabeticService {

    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final int ALPHABET_SIZE = ALPHABET.length();


    private char[][] generateTable() {
        char[][] table = new char[ALPHABET_SIZE][ALPHABET_SIZE];

        for (int i = 0; i < ALPHABET_SIZE; i++) {
            for (int j = 0; j < ALPHABET_SIZE; j++) {
                table[i][j] = ALPHABET.charAt((i + j) % ALPHABET_SIZE);
            }
        }

        return table;
    }

    @Override
    public String encrypt(String plainText, String key) {
        String trimmedText = plainText.replaceAll("\\s+", "");

        char[][] table = generateTable();
        StringBuilder encryptedText = new StringBuilder();

        for (int i = 0, keyIndex = 0; i < trimmedText.length(); i++) {
            char p = trimmedText.charAt(i);
            char k = key.charAt(keyIndex % key.length());

            int row = ALPHABET.indexOf(k);
            int col = ALPHABET.indexOf(p);
            encryptedText.append(table[row][col]);

            keyIndex++;
        }

        return encryptedText.toString();
    }

    @Override
    public String decrypt(String encryptedText, String key) {
        char[][] table = generateTable();
        StringBuilder decryptedText = new StringBuilder();

        for (int i = 0, keyIndex = 0; i < encryptedText.length(); i++) {
            char c = encryptedText.charAt(i);
            char k = key.charAt(keyIndex);

            if (ALPHABET.indexOf(c) >= 0) {
                int row = ALPHABET.indexOf(k);

                for (int col = 0; col < ALPHABET_SIZE; col++) {
                    if (table[row][col] == c) {
                        decryptedText.append(ALPHABET.charAt(col));
                        break;
                    }
                }

                keyIndex = (keyIndex + 1) % key.length();
            } else {
                decryptedText.append(c);
            }
        }

        return decryptedText.toString();
    }

    private boolean isValid(String input) {
        for (char c : input.toCharArray()) {
            if (ALPHABET.indexOf(c) == -1) {
                return false;
            }
        }
        return true;
    }
}
