package com.krjaken.wtf;

import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Component
public class Config extends Properties {
    public Config() throws IOException {
        InputStream input = new FileInputStream("src/main/resources/core/applicationConfig.properties");
        this.load(input);
    }
}
