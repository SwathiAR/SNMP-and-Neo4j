package com.torana.training.snmpServices;


import org.snmp4j.smi.OID;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.*;

/**
 * Created by swathi on 7/27/2015.
 */
public class YamlReaderTest {
    SnmpClientService sc  = new SnmpClientService("192.168.1.64/161");

   /* YamlReader yamlReader = new YamlReader();
    Worker worker = new Worker();

    @Test
    public void displayYamlJavaObject() throws FileNotFoundException {

        yamlReader.provideListOfOids();
    }
    @Test
    public  void createsNode() throws IOException {
        worker.createNodeInDB();
    }*/

@Test
    public void shouldCheckSnmpgetSubtree() throws IOException {
    String asString = sc.getAsString(new OID(".1.3.6.1.2.1.2.2.1.8.34"));
    System.out.println(asString);

}


}