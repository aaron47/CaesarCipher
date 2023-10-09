package com.aaron.encryption.resource;

import com.aaron.encryption.services.caesar_polyalphabetic.CaesarCipherPolyalphabeticService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/polyalphabetic")
@RequiredArgsConstructor
public class EncryptionPolyalphabetic {
    private final String key = "FGfXJUGk}M>c&r~";
    private final CaesarCipherPolyalphabeticService caesarCipherPolyalphabeticService;

    @PostMapping("/encrypt")
    public ResponseEntity<Map<String, String>> encrypt(@RequestBody String text) {
        String encryptedText = this.caesarCipherPolyalphabeticService.encrypt(text, key);

        Map<String, String> response = new HashMap<>();
        response.put("text", encryptedText);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/decrypt")
    public ResponseEntity<Map<String, String>> decrypt(@RequestBody String text) {
        String decryptedText = this.caesarCipherPolyalphabeticService.decrypt(text, key);

        Map<String, String> response = new HashMap<>();
        response.put("text", decryptedText);


        return ResponseEntity.ok(response);
    }
}
