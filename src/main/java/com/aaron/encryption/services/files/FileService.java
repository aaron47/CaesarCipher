package com.aaron.encryption.services.files;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface FileService {
    Map<String, Object> uploadFiles(List<MultipartFile> multipartFiles) throws IOException;

    Map<String, Object> downloadFile(String fileName) throws IOException;
}
