package com.aaron.encryption.services.column_transposition;

import org.springframework.stereotype.Service;

import static java.util.Arrays.*;

@Service
public class ColumnTranspositionServiceImpl implements ColumnTranspositionService {
    @Override
    public String encrypt(String plainText, String key) {
        int[] order = getOrder(key);
        StringBuilder encryptedText = new StringBuilder();

        for (int col = 0; col < order.length; col++) {
            for (int row = order[col]; row < plainText.length(); row += key.length()) {
                encryptedText.append(plainText.charAt(row));
            }
        }

        return encryptedText.toString();
    }

    @Override
    public String decrypt(String encryptedText, String key) {
        int[] order = getOrder(key);
        int numRows = (int) Math.ceil((double) encryptedText.length() / key.length());
        char[][] table = new char[numRows][key.length()];
        int index = 0;

        for (int col = 0; col < order.length; col++) {
            for (int row = 0; row < numRows && index < encryptedText.length(); row++) {
                table[row][order[col]] = encryptedText.charAt(index++);
            }
        }

        StringBuilder plaintext = new StringBuilder();
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < key.length() && table[row][col] != 0; col++) {
                plaintext.append(table[row][col]);
            }
        }

        return plaintext.toString();
    }

    // This method provides an order for the columns based on the key
    private int[] getOrder(String key) {
        int[] order = new int[key.length()];
        char[] keyArray = key.toCharArray();
        sort(keyArray);

        for (int i = 0; i < key.length(); i++) {
            for (int j = 0; j < key.length(); j++) {
                if (key.charAt(i) == keyArray[j]) {
                    order[i] = j;
                    keyArray[j] = '\0'; // Mark as visited
                    break;
                }
            }
        }

        return order;
    }
}
