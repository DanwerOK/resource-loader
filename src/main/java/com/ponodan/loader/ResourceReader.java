package com.ponodan.loader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

@Component
public class ResourceReader {
    @Autowired
    private ResourceLoader resourceLoader;

    public Resource getResourceByPath(String path) {
        Resource resource = resourceLoader.getResource(path);
        // Fallback solution for case when resource resides outside of JAR file and accessed by absolute path.
        if (!resource.exists()) {
            Resource fileResource = resourceLoader.getResource(ResourceUtils.FILE_URL_PREFIX + path);
            if (fileResource.exists()) resource = fileResource;
        }
        return resource;
    }
}
