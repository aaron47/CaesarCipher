package com.aaron.encryption.services.analyzers;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.*;


@Service
public class CaesarCipherAnalyzerServiceImpl implements CaesarCipherAnalyzerService {
    private static final String FRENCH_FREQUENCIES = "easitnruol";

    public String guessAndDecrypt(String ciphertext) {
        String text = ciphertext.toLowerCase().replaceAll("[^a-z]", "");
        Map<Character, Long> letterFrequencies = calculateLetterFrequencies(text);

        char mostFrequentChar = letterFrequencies.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .get()
                .getKey();

        int likelyShift = mostFrequentChar - 'e';

        return decrypt(ciphertext, likelyShift);
    }

    private Map<Character, Long> calculateLetterFrequencies(String text) {
        return text.chars()
                .mapToObj(c -> (char) c)
                .collect(groupingBy(c -> c, counting()));
    }

    private String decrypt(String text, int shift) {
        StringBuilder result = new StringBuilder();

        for (char ch : text.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                char c = (char) ((ch - shift - 'A' + 26) % 26 + 'A');
                result.append(c);
            } else if (Character.isLowerCase(ch)) {
                char c = (char) ((ch - shift - 'a' + 26) % 26 + 'a');
                result.append(c);
            } else {
                result.append(ch);
            }
        }

        return result.toString();
    }
}
