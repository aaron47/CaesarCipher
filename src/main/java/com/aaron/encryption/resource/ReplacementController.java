package com.aaron.encryption.resource;

import com.aaron.encryption.services.replacement.ReplacementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/replacement")
@RequiredArgsConstructor
public class ReplacementController {
    private final ReplacementService replacementService;

    @PostMapping("/encrypt")
    public ResponseEntity<Map<String, String>> encrypt(@RequestBody String text) {
        String encryptedText = this.replacementService.encrypt(text);

        Map<String, String> response = new HashMap<>();
        response.put("text", encryptedText);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/decrypt")
    public ResponseEntity<Map<String, String>> decrypt(@RequestBody String text) {
        String decryptedText = this.replacementService.decrypt(text);

        Map<String, String> response = new HashMap<>();
        response.put("text", decryptedText);

        return ResponseEntity.ok(response);
    }
}
