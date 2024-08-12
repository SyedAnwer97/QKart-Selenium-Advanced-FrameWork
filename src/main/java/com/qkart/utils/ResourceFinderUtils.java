package com.qkart.utils;

import lombok.SneakyThrows;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public final class ResourceFinderUtils {

    private ResourceFinderUtils() {
    }

    @SneakyThrows
    public static InputStream getResource(String filePath) {
        InputStream resourceAsStream = ResourceFinderUtils.class.getClassLoader().getResourceAsStream(filePath);
        if (Objects.nonNull(resourceAsStream)) {
            return resourceAsStream;
        }
        return Files.newInputStream(Path.of(filePath));
    }

}