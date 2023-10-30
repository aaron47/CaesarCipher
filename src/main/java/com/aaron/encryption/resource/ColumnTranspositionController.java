package com.aaron.encryption.resource;

import com.aaron.encryption.services.column_transposition.ColumnTranspositionService;
import com.aaron.encryption.services.files.FileService;
import com.aaron.encryption.utils.Algorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    @PostMapping("/upload/decrypt")
    public ResponseEntity<Map<String, Object>> uploadFilesAndDecrypt(@RequestParam("files") List<MultipartFile> multipartFiles, @RequestParam("key") String key) throws IOException {
        Map<String, Object> response = this.fileService.uploadFilesAndDecrypt(multipartFiles, Algorithm.COLUMN_TRANSPOSITION, Optional.empty(), Optional.of(key));
        return ResponseEntity.ok().body(response);
    }
}
