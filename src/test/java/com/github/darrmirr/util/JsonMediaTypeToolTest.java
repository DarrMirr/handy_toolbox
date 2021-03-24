package com.github.darrmirr.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JsonMediaTypeToolTest {
    private MediaTypeTool jsonMediaTypeTool = new JsonMediaTypeTool(new ObjectMapper());
    private String prettyJson = "{" + System.lineSeparator() +
            "  \"foo_key\" : \"foo_value\"," + System.lineSeparator() +
            "  \"bar_key\" : \"bar_value\"" + System.lineSeparator() +
            "}";
    private String minifiedJson = "{\"foo_key\":\"foo_value\",\"bar_key\":\"bar_value\"}";

    @Test
    void prettyPrint() {
        var actualPrettyJson = jsonMediaTypeTool.prettyPrint(minifiedJson);
        assertEquals(prettyJson, actualPrettyJson);
    }

    @Test
    void minify() {
        var actualMinifiedJson = jsonMediaTypeTool.minify(prettyJson);
        assertEquals(minifiedJson, actualMinifiedJson);
    }

    @Test
    void typeName() {
        assertEquals("JSON", jsonMediaTypeTool.typeName());
    }
}