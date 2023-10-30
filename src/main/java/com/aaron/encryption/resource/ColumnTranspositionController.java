package com.aaron.encryption.resource;

import com.aaron.encryption.services.column_transposition.ColumnTranspositionService;
import com.aaron.encryption.services.files.FileService;
import com.aaron.encryption.utils.Algorithm;
import com.aaron.encryption.utils.AlgorithmWithKeyRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/column_transposition")
@RequiredArgsConstructor
public class ColumnTranspositionController {
    private final ColumnTranspositionService columnTranspositionService;
    private final FileService fileService;

    @PostMapping("/upload/encrypt")
    public ResponseEntity<Map<String, Object>> uploadFilesAndEncrypt(@RequestParam("files") List<MultipartFile> multipartFiles, @RequestParam("key") String key) throws IOException {
        Map<String, Object> response = this.fileService.uploadFilesAndEncrypt(multipartFiles, Algorithm.COLUMN_TRANSPOSITION, Optional.empty(), Optional.of(key));
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/decrypt")
    public ResponseEntity<Map<String, String>> decrypt(@RequestBody AlgorithmWithKeyRequest algorithmWithKeyRequest) {
        String decryptedText = this.columnTranspositionService.decrypt(algorithmWithKeyRequest.getText(), algorithmWithKeyRequest.getKey());

        Map<String, String> response = new HashMap<>();
        response.put("text", decryptedText);


        return ResponseEntity.ok(response);
    }

}
