package com.aaron.encryption.services.files;

import com.aaron.encryption.utils.Algorithm;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface FileService {
    Map<String, Object> uploadFilesAndEncrypt(List<MultipartFile> multipartFiles, Algorithm algorithm, Optional<Integer> shift, Optional<String> key) throws IOException;

    Map<String, Object> uploadFilesAndDecrypt(List<MultipartFile> multipartFiles, Algorithm algorithm, Optional<Integer> shift, Optional<String> key) throws IOException;

    Map<String, Object> downloadFile(String fileName) throws IOException;
}
