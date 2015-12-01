package test.java.com.pernix.smartquote.transformer;

import main.java.com.pernix.smartquote.resources.ResourceManager;
import main.java.com.pernix.smartquote.transformer.TransformerEngine;
import org.junit.Assert;
import org.junit.Test;

public class TransformerEngineTest {
    TransformerEngine transformerEngine;

    @Test
    public void testCreateTransformerEngine(){
        transformerEngine = new TransformerEngine();
        Assert.assertNotNull(transformerEngine);
    }

    @Test
    public void testTransformXMLtoHTML(){
        ResourceManager resourceManager = new ResourceManager();
        String xml = resourceManager.getFileAsString("XMLData");
        String expectedHTML = resourceManager.getFileAsString("ExpectedHTML");
        transformerEngine = new TransformerEngine();
        String result = transformerEngine.transformXMLtoHTML(xml);
        Assert.assertEquals(expectedHTML, result);
    }
}
