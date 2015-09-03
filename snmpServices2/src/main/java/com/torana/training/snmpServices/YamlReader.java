package com.torana.training.snmpServices;

import com.torana.training.model.Instance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Created by swathi on 7/20/2015.
 */
public class YamlReader {
    Logger logger = LoggerFactory.getLogger(YamlReader.class);


    public List<Instance> provideListOfOids() throws FileNotFoundException {

        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("Switch.yaml");
        Yaml yaml = new Yaml();
        Map<String, String> load = (Map<String ,String>) yaml.load(inputStream);

        List<Instance> listOfInstances = new ArrayList<Instance>();
        for(String key : load.keySet()){
            Instance instance = new Instance(key , load.get(key));
            listOfInstances.add(instance);
        }
        return listOfInstances;

    }
}



