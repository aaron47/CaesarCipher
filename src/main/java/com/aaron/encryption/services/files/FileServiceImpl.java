package com.aaron.encryption.services.files;

import com.aaron.encryption.services.caesar.CaesarCipher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.nio.file.Files.copy;
import static java.nio.file.Paths.get;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {
    private final CaesarCipher caesarCipher;
    private static final int SHIFT = 3;

    private static final String DIRECTORY = System.getProperty("user.home") + "/Downloads/";


    @Override
    public Map<String, Object> uploadFiles(List<MultipartFile> multipartFiles) throws IOException {
        List<String> fileNames = new ArrayList<>();
        Map<String, Object> response = new HashMap<>();

        for (MultipartFile file : multipartFiles) {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            Path fileStorage = get(DIRECTORY, fileName).toAbsolutePath().normalize();
            copy(file.getInputStream(), fileStorage, REPLACE_EXISTING);


            if (fileName.endsWith(".txt")) {
                String content = new String(file.getBytes(), StandardCharsets.UTF_8);
                String encryptedText = this.caesarCipher.encrypt(content, SHIFT);
                response.put("encrypted_text", encryptedText);
                System.out.println("Contents of " + fileName + ": " + content);
            }

            fileNames.add(fileName);
        }

        response.put("file_names", fileNames);
        return response;
    }

    @Override
    public Map<String, Object> downloadFile(String fileName) {
        return null;
    }
}
