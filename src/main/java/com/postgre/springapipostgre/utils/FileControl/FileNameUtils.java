package com.postgre.springapipostgre.utils.FileControl;

public class FileNameUtils {
    public static String generateSafeFileName(String originalFileName) {
        return originalFileName.replaceAll("[^a-zA-Z0-9.\\-_]", "_");
    }
}
