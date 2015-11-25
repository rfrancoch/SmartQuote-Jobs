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

    private final Logger logger = LoggerFactory.getLogger(TransformerEngine.class);

    public static String transformXMLtoHTML(String xml){
        String result ="";
        try {
            Element node = (Element) DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new ByteArrayInputStream(xml.getBytes("UTF-8"))).getDocumentElement();
            URL htmlXLSOutput = ClassLoader.getSystemResource("style.xsl");
            result = transform(node, htmlXLSOutput);
        } catch (IOException | ParserConfigurationException | SAXException e) {
            e.printStackTrace();
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
            processedDocument = ((ByteArrayOutputStream) ((StreamResult) result).getOutputStream()).toString("utf-8");
        } catch (TransformerException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return processedDocument;
    }
}
