package com.github.darrmirr.util;

import com.fasterxml.jackson.databind.ObjectMapper;

public final class ToolFactory {
    public static final String JSON_TYPE = "JSON";
    public static final String XML_TYPE = "XML";

    private ToolFactory() {
    }

    public static MediaTypeTool get(String type) {
        if (JSON_TYPE.equalsIgnoreCase(type)) {
            return new JsonMediaTypeTool(new ObjectMapper());
        }
        if (XML_TYPE.equalsIgnoreCase(type)) {
           return new XmlMediaTypeTool();
        }
        throw new IllegalArgumentException("Type '" + type + "' is not supported");
    }
}
