package com.postgre.springapipostgre.utils;

import com.postgre.springapipostgre.utils.FileControl.FileStorage;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class SaveFile {
    private static final String baseDir = "media/attachments";

    public static void save(MultipartFile file, String dirTarget) throws IOException {
        FileStorage fileStorage = new FileStorage(baseDir);
        fileStorage.saveFile(file, dirTarget);

    }
}
