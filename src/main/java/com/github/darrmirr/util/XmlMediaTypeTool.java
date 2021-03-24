package com.github.darrmirr.util;

import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;
import java.io.StringWriter;

public class XmlMediaTypeTool implements MediaTypeTool {
    private final static String TRIM_WHITESPACES_XLST = "<?xml version=\"1.0\"?>\n" +
            "<xsl:stylesheet version=\"1.0\" xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\">\n" +
            "    <xsl:output indent=\"no\" />\n" +
            "    <xsl:strip-space elements=\"*\"/>\n" +
            "    <xsl:template match=\"@*|node()\">\n" +
            "        <xsl:copy>\n" +
            "            <xsl:apply-templates select=\"@*|node()\"/>\n" +
            "        </xsl:copy>\n" +
            "    </xsl:template>\n" +
            "</xsl:stylesheet>";
    private TransformerFactory transformerFactory = TransformerFactory.newInstance();

    @Override
    public String prettyPrint(String content) {
        try {
            var minifiedContent = minify(content);
            if (minifiedContent.contains("Exception")) {
                return minifiedContent;
            }
            var result = getEmptyResult();
            prettyTransformer().transform(toStreamSource(minifiedContent), result);
            return result.getWriter().toString();
        } catch (TransformerException e) {
            return e.getMessage();
        }
    }

    @Override
    public String minify(String content) {
        try {
            var result = getEmptyResult();
            minifyTransformer().transform(toStreamSource(content), result);
            return result.getWriter().toString();
        } catch (TransformerException e) {
            return e.getMessage();
        }
    }

    @Override
    public String typeName() {
        return "XML";
    }

    private StreamSource toStreamSource(String content) {
        return new StreamSource(new StringReader(content));
    }

    private StreamResult getEmptyResult() {
        return new StreamResult(new StringWriter());
    }

    private Transformer minifyTransformer() throws TransformerConfigurationException {
        var transformer = transformerFactory.newTransformer(toStreamSource(TRIM_WHITESPACES_XLST));
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        transformer.setOutputProperty(OutputKeys.INDENT, "no");
        return transformer;
    }

    private Transformer prettyTransformer() throws TransformerConfigurationException {
        var transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        return transformer;
    }
}
