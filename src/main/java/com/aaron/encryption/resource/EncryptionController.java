package com.aaron.encryption.resource;

import com.aaron.encryption.services.caesar.CaesarCipher;
import com.aaron.encryption.services.files.FileService;
import com.aaron.encryption.utils.Algorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.nio.file.Files.copy;
import static java.nio.file.Paths.get;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static org.springframework.http.HttpHeaders.CONTENT_DISPOSITION;

@RestController
@RequestMapping("/api/v1/caesar")
@RequiredArgsConstructor
public class EncryptionController {
    private final CaesarCipher caesarCipher;
    private final FileService fileService;
    private static final int SHIFT = 3;

    private static final String DIRECTORY = System.getProperty("user.home") + "/Downloads/";

    @PostMapping("/encrypt")
    public ResponseEntity<Map<String, String>> encrypt(@RequestBody String text) {
        String encryptedText = this.caesarCipher.encrypt(text, SHIFT);

        Map<String, String> response = new HashMap<>();
        response.put("text", encryptedText);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/decrypt")
    public ResponseEntity<Map<String, String>> decrypt(@RequestBody String text) {
        String decryptedText = this.caesarCipher.decrypt(text, SHIFT);

        Map<String, String> response = new HashMap<>();
        response.put("text", decryptedText);

        return ResponseEntity.ok(response);
    }


    @PostMapping("/upload/encrypt")
    public ResponseEntity<Map<String, Object>> uploadFilesAndEncrypt(@RequestParam("files") List<MultipartFile> multipartFiles) throws IOException {
        Map<String, Object> response = this.fileService.uploadFilesAndEncrypt(multipartFiles, Algorithm.CAESER_CIPHER);
        return ResponseEntity.ok().body(response);
    }


    @PostMapping("/upload/decrypt")
    public ResponseEntity<Map<String, Object>> uploadFilesAndDecrypt(@RequestParam("files") List<MultipartFile> multipartFiles) throws IOException {
        Map<String, Object> response = this.fileService.uploadFilesAndDecrypt(multipartFiles, Algorithm.CAESER_CIPHER);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<Resource> downloadFiles(@PathVariable("fileName") String fileName) throws IOException {
        Path filePath = get(DIRECTORY).toAbsolutePath().normalize().resolve(fileName);
        if (!Files.exists(filePath)) {
            throw new FileNotFoundException("The file with the name " + fileName + " was not found on the server");
        }

        Resource resource = new UrlResource(filePath.toUri());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("File-Name", fileName);
        httpHeaders.add(CONTENT_DISPOSITION, "attachement;File-Name=" + resource.getFilename());

        return ResponseEntity.ok().contentType(MediaType.parseMediaType(Files.probeContentType(filePath))).headers(httpHeaders).body(resource);
    }
}
