package com.aaron.encryption.resource;

import com.aaron.encryption.services.CaesarCipher;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/caesar")
@RequiredArgsConstructor
public class EncryptionController {
    private final CaesarCipher caesarCipher;
    private static final int shift = 3;

    @PostMapping("/encrypt")
    public ResponseEntity<String> encrypt(@RequestBody String text) {
        String encryptedText = this.caesarCipher.encrypt(text, shift);

        return ResponseEntity.ok(encryptedText);
    }

    @PostMapping("/decrypt")
    public ResponseEntity<String> decrypt(@RequestBody String text) {
        String decryptedText = this.caesarCipher.decrypt(text, shift);

        return ResponseEntity.ok(decryptedText);
    }
}
