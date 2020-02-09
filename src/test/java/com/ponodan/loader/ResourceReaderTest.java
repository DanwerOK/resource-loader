package com.ponodan.loader;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-context.xml"})
public class ResourceReaderTest {
    @Autowired
    private ResourceReader resourceLoader;

    @Test
    public void testGetClasspathResource() {
        String path = "classpath:/spec/openapi.yaml";
        Resource resource = resourceLoader.getResourceByPath(path);
        Assert.assertNotNull(resource);
        Assert.assertTrue(resource.exists());
        printResourceDetails(resource);
    }

    @Test
    public void testGetAbsolutePathResource() {
        String path = "D:/TEMP/openapi.yaml";
        Resource resource = resourceLoader.getResourceByPath(path);
        Assert.assertNotNull(resource);
        Assert.assertTrue(resource.exists());
        printResourceDetails(resource);
    }

    @Test
    public void testGetFilePathResource() {
        String path = "file:D:/TEMP/openapi.yaml";
        Resource resource = resourceLoader.getResourceByPath(path);
        Assert.assertNotNull(resource);
        Assert.assertTrue(resource.exists());
        printResourceDetails(resource);
    }

    @Test
    public void testGetNotExistingResource() {
        String path = "classpath:/spec/openapi.json";
        Resource resource = resourceLoader.getResourceByPath(path);
        Assert.assertNotNull(resource);
        Assert.assertFalse(resource.exists());
    }

    private void printResourceDetails(Resource resource) {
        Assert.assertTrue(resource.isFile());
        Assert.assertFalse(resource.isOpen());
        Assert.assertTrue(resource.exists());

        System.out.println(String.format("getDescription: %s", resource.getDescription()));
        System.out.println(String.format("getFilename: %s", resource.getFilename()));

        try {
            System.out.println(String.format("getURI: %s", resource.getURI()));
            System.out.println(String.format("getURL: %s", resource.getURL()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
