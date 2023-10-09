package com.aaron.encryption.services.replacement;

import org.springframework.stereotype.Service;

@Service
public class ReplacementServiceImpl implements ReplacementService {
    private final char[] ORIGINAL_CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    private final char[] REPLACEMENT_CHARS = "qazwsxedcrfvtgbyhnujmikolpQAZWSXEDCRFVTGBYHNUJMIKOLP".toCharArray();

    @Override
    public String encrypt(String text) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            int index = new String(ORIGINAL_CHARS).indexOf(ch);

            if (index != -1) {
                result.append(REPLACEMENT_CHARS[index]);
            } else {
                result.append(ch);
            }
        }
        return result.toString();
    }

    @Override
    public String decrypt(String encryptedText) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < encryptedText.length(); i++) {
            char ch = encryptedText.charAt(i);
            int index = new String(REPLACEMENT_CHARS).indexOf(ch);

            if (index != -1) {
                result.append(ORIGINAL_CHARS[index]);
            } else {
                result.append(ch);
            }
        }
        return result.toString();
    }
}
