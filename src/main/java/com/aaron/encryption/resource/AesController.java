package com.aaron.encryption.resource;

import com.aaron.encryption.services.aes.AesService;
import com.aaron.encryption.services.files.FileService;
import com.aaron.encryption.utils.Algorithm;
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
@RequestMapping("/api/v1/aes")
@RequiredArgsConstructor
public class AesController {
    private final FileService fileService;
    private final AesService aesService;

    @PostMapping("/upload/encrypt")
    public ResponseEntity<Map<String, Object>> uploadFilesAndEncrypt(@RequestParam("files") List<MultipartFile> multipartFiles) throws Exception {
        Map<String, Object> response = this.fileService.uploadFilesAndEncrypt(multipartFiles, Algorithm.AES, Optional.empty(), Optional.empty());

        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/upload/decrypt")
    public ResponseEntity<Map<String, Object>> uploadFilesAndDecrypt(@RequestParam("files") List<MultipartFile> multipartFiles) throws Exception {
        Map<String, Object> response = this.fileService.uploadFilesAndDecrypt(multipartFiles, Algorithm.AES, Optional.empty(), Optional.empty());

        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/decrypt")
    public ResponseEntity<Map<String, String>> decrypt(@RequestBody String text) throws Exception {
        String decryptedText = this.aesService.decrypt(text);

        Map<String, String> response = new HashMap<>();
        response.put("text", decryptedText);

        return ResponseEntity.ok(response);
    }
}
