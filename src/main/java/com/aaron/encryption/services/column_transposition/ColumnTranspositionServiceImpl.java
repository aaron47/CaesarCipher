package com.aaron.encryption.services.column_transposition;

import org.springframework.stereotype.Service;

import java.util.Arrays;

import static java.util.Arrays.*;

@Service
public class ColumnTranspositionServiceImpl implements ColumnTranspositionService {
    @Override
    public String encrypt(String text, String key) {
        int[] order = getOrder(key);
        StringBuilder encryptedText = new StringBuilder();

        for (int i = 0; i < order.length; i++) {
            int j = order[i];
            while (j < text.length()) {
                encryptedText.append(text.charAt(j));
                j += key.length();
            }
        }

        return encryptedText.toString();
    }

    @Override
    public String decrypt(String encryptedText, String key) {
        int[] order = getOrder(key);
        int columns = key.length();
        int rows = (int) Math.ceil((double) encryptedText.length() / columns);
        char[][] grid = new char[rows][columns];

        int k = 0;
        for (int col : order) {
            for (int row = 0; row < rows; row++) {
                if (k < encryptedText.length()) {
                    grid[row][col] = encryptedText.charAt(k++);
                } else {
                    grid[row][col] = ' ';
                }
            }
        }

        StringBuilder decryptedText = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (grid[i][j] != ' ') {
                    decryptedText.append(grid[i][j]);
                }
            }
        }

        return decryptedText.toString();
    }

    private int[] getOrder(String key) {
        int[] order = new int[key.length()];
        char[] keyArray = key.toCharArray();
        char[] sortedKeyArray = key.toCharArray();
        Arrays.sort(sortedKeyArray);

        for (int i = 0; i < key.length(); i++) {
            order[i] = new String(keyArray).indexOf(sortedKeyArray[i]);
            keyArray[order[i]] = ' ';
        }

        return order;
    }
}
