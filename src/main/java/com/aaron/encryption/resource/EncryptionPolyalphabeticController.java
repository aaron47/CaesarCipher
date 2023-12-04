package com.aaron.encryption.resource;

import com.aaron.encryption.services.caesar_polyalphabetic.CaesarCipherPolyalphabeticService;
import com.aaron.encryption.services.files.FileService;
import com.aaron.encryption.utils.Algorithm;
import com.aaron.encryption.utils.AlgorithmWithKeyRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/polyalphabetic")
@RequiredArgsConstructor
public class EncryptionPolyalphabeticController {
    private final String key = "FGfXJUGk}M>c&r~";
    private final CaesarCipherPolyalphabeticService caesarCipherPolyalphabeticService;
    private final FileService fileService;

    @PostMapping("/encrypt")
    public ResponseEntity<Map<String, String>> encrypt(@RequestBody AlgorithmWithKeyRequest algorithmWithKeyRequest) {
        String encryptedText = this.caesarCipherPolyalphabeticService.encrypt(algorithmWithKeyRequest.getText(), algorithmWithKeyRequest.getKey());

        Map<String, String> response = new HashMap<>();
        response.put("text", encryptedText);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/upload/encrypt")
    public ResponseEntity<Map<String, Object>> uploadFilesAndEncrypt(@RequestParam("files") List<MultipartFile> multipartFiles, @RequestParam("key") String key) throws Exception {
        Map<String, Object> response = this.fileService.uploadFilesAndEncrypt(multipartFiles, Algorithm.CAESER_CIPHER_POLYALPHABETIC, Optional.empty(), Optional.of(key));
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/upload/decrypt")
    public ResponseEntity<Map<String, Object>> uploadFilesAndDecrypt(@RequestParam("files") List<MultipartFile> multipartFiles, @RequestParam("key") String key) throws Exception {
        Map<String, Object> response = this.fileService.uploadFilesAndDecrypt(multipartFiles, Algorithm.CAESER_CIPHER_POLYALPHABETIC, Optional.empty(), Optional.of(key));
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/decrypt")
    public ResponseEntity<Map<String, String>> decrypt(@RequestBody AlgorithmWithKeyRequest algorithmWithKeyRequest) {
        String decryptedText = this.caesarCipherPolyalphabeticService.decrypt(algorithmWithKeyRequest.getText(), algorithmWithKeyRequest.getKey());

        Map<String, String> response = new HashMap<>();
        response.put("text", decryptedText);


        return ResponseEntity.ok(response);
    }
}
