package com.aaron.encryption.resource;

import com.aaron.encryption.services.analyzers.CaesarCipherAnalyzerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/analyze")
@RequiredArgsConstructor
public class AnalyzerController {
    private final CaesarCipherAnalyzerService caesarCipherAnalyzerService;

    @PostMapping("")
    public ResponseEntity<Map<String, String>> analyze(@RequestBody String text) {
        Map<String, String> response = new HashMap<>();
        String guessedText = caesarCipherAnalyzerService.guessAndDecrypt(text);
        response.put("guessedText", guessedText);
        return ResponseEntity.ok(response);
    }
}
