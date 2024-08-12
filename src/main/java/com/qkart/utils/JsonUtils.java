package com.qkart.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qkart.exception.JsonFileNotFoundException;

import java.io.InputStream;

public final class JsonUtils {

    private JsonUtils() {

    }

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static String getTestData(String path) {
        try (InputStream inputStream = ResourceFinderUtils.getResource(path)) {
            return OBJECT_MAPPER.readValue(inputStream, String.class);
        } catch (Exception e) {
            throw new JsonFileNotFoundException("The json file is not present in location. Please check the json file location!!!");

        }
    }
}
