package com.ponodan.loader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class ResourceLoader {
    private Resource openapiSpec;

    public Resource getOpenapiSpec() {
        return openapiSpec;
    }

    @Autowired
    public void setOpenapiSpec(Resource openapiSpec) {
        this.openapiSpec = openapiSpec;
    }

    public void printOpenapiSpecDetails() {
        openapiSpec.isFile();
        openapiSpec.isOpen();
        openapiSpec.getDescription();
        openapiSpec.getFilename();
    }
}
