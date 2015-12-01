package main.java.com.pernix.smartquote.transformer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Templates;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class TransformerEngine {

    public static final String UTF_8 = "UTF-8";
    public static final String STYLE_XSL = "style.xsl";
    private final static Logger logger = LoggerFactory.getLogger(TransformerEngine.class);

    public static String transformXMLtoHTML(String xml){
        String result = null;
        try {
            Element node = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new ByteArrayInputStream(xml.getBytes(UTF_8))).getDocumentElement();
            URL htmlXLSOutput = ClassLoader.getSystemResource(STYLE_XSL);
            result = transform(node, htmlXLSOutput);
        } catch (IOException | ParserConfigurationException | SAXException e) {
            logger.error("IOException, ParserConfigurationException or SAXException at transformXMLtoHTML in Transformer Engine " + e.getMessage());
        }
        return result;
    }

    private static String transform(org.w3c.dom.Element xmlDocument, URL htmlXslOutPut) {

        Result result = new StreamResult(new ByteArrayOutputStream());
        String processedDocument = "";
        try {
            TransformerFactory factory = TransformerFactory.newInstance();

            Source xmlInput = new DOMSource(xmlDocument);
            StreamSource xslStream = new StreamSource(new File(htmlXslOutPut.getFile()));
            Transformer transformer = factory.newTransformer(xslStream);
            Templates xsl = factory.newTemplates(xslStream);
            transformer.transform(xmlInput, result);
            processedDocument = ((ByteArrayOutputStream) ((StreamResult) result).getOutputStream()).toString(UTF_8);
        } catch (TransformerException | UnsupportedEncodingException e) {
            logger.error("TransformerException or UnsupportedEncodingException at transform in TransformEngine " + e.getMessage());
        }
        return processedDocument;
    }
}
