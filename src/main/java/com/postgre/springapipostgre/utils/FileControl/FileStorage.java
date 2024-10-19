package com.postgre.springapipostgre.utils.FileControl;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class FileStorage {
    private final String baseDir;

    public FileStorage(String baseDir) {
        this.baseDir = baseDir;
    }

    public void saveFile(MultipartFile file, String dirTarget) throws IOException {
        String uploadDir = baseDir + dirTarget;
        createDirectoryIfNotExists(uploadDir);

        String safeFileName = FileNameUtils.generateSafeFileName(Objects.requireNonNull(file.getOriginalFilename()));
        Path filePath = Paths.get(uploadDir, safeFileName);

        if (Files.exists(filePath)) {
            throw new IOException("File already exists: " + filePath.toString());
        }
        Files.copy(file.getInputStream(), filePath);
    }

    private void createDirectoryIfNotExists(String dirPath) {
        File directory = new File(dirPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

}
