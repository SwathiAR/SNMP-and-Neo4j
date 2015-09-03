package com.torana.training.snmpServices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;


/**
 * Created by swathi on 7/20/2015.
 */
public class YamlReader {
    Logger logger = LoggerFactory.getLogger(YamlReader.class);


    public List<Map<String, String>> provideOIDLIst() throws FileNotFoundException {

        InputStream inputStream = new FileInputStream(new File("S:\\Work\\Training\\java\\snmpServices\\src\\main\\resources\\Switch.yaml"));
        Yaml yaml = new Yaml();
        Map<String , Object> yamlInMap = (Map<String, Object>) yaml.load(inputStream);
        List<Map<String,String>> oidList = null;
        for(String key : yamlInMap.keySet()){
            if(key.equals("OIDList")){
                oidList = (List<Map<String, String>>) yamlInMap.get(key);
            }
        }
        return  oidList;
    }
}


