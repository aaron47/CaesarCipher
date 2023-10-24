package com.aaron.encryption.resource;

import com.aaron.encryption.services.files.FileService;
import com.aaron.encryption.services.replacement.ReplacementService;
import com.aaron.encryption.utils.Algorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/replacement")
@RequiredArgsConstructor
public class ReplacementController {
    private final ReplacementService replacementService;
    private final FileService fileService;

    @PostMapping("/encrypt")
    public ResponseEntity<Map<String, String>> encrypt(@RequestBody String text) {
        String encryptedText = this.replacementService.encrypt(text);

        Map<String, String> response = new HashMap<>();
        response.put("text", encryptedText);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/upload/encrypt")
    public ResponseEntity<Map<String, Object>> uploadFilesAndEncrypt(@RequestParam("files") List<MultipartFile> multipartFiles) throws IOException {
        Map<String, Object> response = this.fileService.uploadFilesAndEncrypt(multipartFiles, Algorithm.REPLACEMENT);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/upload/decrypt")
    public ResponseEntity<Map<String, Object>> uploadFilesAndDecrypt(@RequestParam("files") List<MultipartFile> multipartFiles) throws IOException {
        Map<String, Object> response = this.fileService.uploadFilesAndDecrypt(multipartFiles, Algorithm.REPLACEMENT);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/decrypt")
    public ResponseEntity<Map<String, String>> decrypt(@RequestBody String text) {
        String decryptedText = this.replacementService.decrypt(text);

        Map<String, String> response = new HashMap<>();
        response.put("text", decryptedText);

        return ResponseEntity.ok(response);
    }
}
