package com.github.darrmirr.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonMediaTypeTool implements MediaTypeTool {
    private final ObjectMapper mapper;

    public JsonMediaTypeTool(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public String prettyPrint(String content) {
        try {
            return mapper
                    .readTree(content)
                    .toPrettyString();
        } catch (JsonProcessingException e) {
            return e.getMessage();
        }
    }

    @Override
    public String minify(String content) {
        try {
            return mapper
                    .readTree(content)
                    .toString();
        } catch (JsonProcessingException e) {
            return e.getMessage();
        }
    }

    @Override
    public String typeName() {
        return "JSON";
    }
}
