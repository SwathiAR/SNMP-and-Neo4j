package com.torana.training.snmpServices;

import static org.testng.Assert.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

import static org.testng.Assert.*;

/**
 * Created by swathi on 7/21/2015.
 */
public class YamlReaderTest {
    Logger logger = LoggerFactory.getLogger(YamlReaderTest.class);
    YamlReader yamlReader = new YamlReader();

    @Test
    public void shouldReadYaml() throws FileNotFoundException {
        yamlReader.provideOIDLIst();
    }

}