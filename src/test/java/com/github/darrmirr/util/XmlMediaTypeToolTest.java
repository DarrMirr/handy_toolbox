package com.github.darrmirr.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class XmlMediaTypeToolTest {
    private MediaTypeTool xmlMediaTypeTool = new XmlMediaTypeTool();
    private String prettyXml = "<foo>" + System.lineSeparator() +
            "    <bar>value</bar>" + System.lineSeparator() +
            "</foo>" + System.lineSeparator();
    private String minifiedXml = "<foo><bar>value</bar></foo>";

    @Test
    void prettyPrintXml() {
        var actualPrettyXml = xmlMediaTypeTool.prettyPrint(minifiedXml);
        assertEquals(prettyXml, actualPrettyXml);
    }

    @Test
    void prettyPrintXmlWithSpaces() {
        var actualPrettyXml = xmlMediaTypeTool.prettyPrint(prettyXml);
        assertEquals(prettyXml, actualPrettyXml);
    }

    @Test
    void minifyXml() {
        var actualMinifiedXml = xmlMediaTypeTool.minify(prettyXml);
        assertEquals(minifiedXml, actualMinifiedXml);
    }

    @Test
    void typeName() {
        assertEquals("XML", xmlMediaTypeTool.typeName());
    }
}