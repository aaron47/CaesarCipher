package com.aaron.encryption.services.files;

import com.aaron.encryption.services.caesar.CaesarCipher;
import com.aaron.encryption.services.caesar_polyalphabetic.CaesarCipherPolyalphabeticService;
import com.aaron.encryption.services.replacement.ReplacementService;
import com.aaron.encryption.utils.Algorithm;
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
    private final CaesarCipherPolyalphabeticService caesarCipherPolyalphabeticService;
    private final ReplacementService replacementService;

    private static final int SHIFT = 3;
    private static final String DIRECTORY = System.getProperty("user.home") + "/Downloads/";
    private static final String key = "FGfXJUGk}M>c&r~";

    @Override
    public Map<String, Object> uploadFilesAndEncrypt(List<MultipartFile> multipartFiles, Algorithm algorithm) throws IOException {
        List<String> fileNames = new ArrayList<>();
        Map<String, Object> response = new HashMap<>();
        String encryptedText = "";

        for (MultipartFile file : multipartFiles) {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            Path fileStorage = get(DIRECTORY, fileName).toAbsolutePath().normalize();
            copy(file.getInputStream(), fileStorage, REPLACE_EXISTING);


            if (fileName.endsWith(".txt")) {
                String content = new String(file.getBytes(), StandardCharsets.UTF_8);

                switch (algorithm) {
                    case CAESER_CIPHER -> encryptedText = this.caesarCipher.encrypt(content, SHIFT);
                    case CAESER_CIPHER_POLYALPHABETIC -> encryptedText =  this.caesarCipherPolyalphabeticService.encrypt(content, key);
                    case REPLACEMENT -> encryptedText = this.replacementService.encrypt(content);
                }


                response.put("encrypted_text", encryptedText);
            }

            fileNames.add(fileName);
        }

        response.put("file_names", fileNames);
        return response;
    }

    @Override
    public Map<String, Object> uploadFilesAndDecrypt(List<MultipartFile> multipartFiles, Algorithm algorithm) throws IOException {
        List<String> fileNames = new ArrayList<>();
        Map<String, Object> response = new HashMap<>();
        String decryptedText = "";

        for (MultipartFile file : multipartFiles) {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            Path fileStorage = get(DIRECTORY, fileName).toAbsolutePath().normalize();
            copy(file.getInputStream(), fileStorage, REPLACE_EXISTING);


            if (fileName.endsWith(".txt")) {
                String content = new String(file.getBytes(), StandardCharsets.UTF_8);

                switch (algorithm) {
                    case CAESER_CIPHER -> decryptedText = this.caesarCipher.decrypt(content, SHIFT);
                    case CAESER_CIPHER_POLYALPHABETIC -> decryptedText =  this.caesarCipherPolyalphabeticService.decrypt(content, key);
                    case REPLACEMENT -> decryptedText = this.replacementService.decrypt(content);
                }

                response.put("decrypted_text", decryptedText);
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
