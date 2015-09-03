package com.torana.training.snmpServices;
import com.google.gson.Gson;
import com.torana.training.model.Instance;
import com.torana.training.model.OidNameAndValue;
import org.snmp4j.smi.OID;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by swathi on 7/20/2015.
 */
public class Worker {
    static final  String baseUrl = "http://localhost:7474/db/data";

    public Map<String , String> getOidNameAndOidValue() throws IOException {
        YamlReader yamlReader = new YamlReader();
        SnmpClientService snmpClientService = new SnmpClientService("udp:192.168.1.64/161");

        List<Instance> listOfOIDsAndNames = yamlReader.provideListOfOids();
        Map<String , String> mapOfOidNameAndOidValue = new HashMap<String, String>();
        for (Instance instance : listOfOIDsAndNames) {
            String oidValue = snmpClientService.getAsString(new OID(instance.getOid()));
            instance.setOidValue(oidValue);
            mapOfOidNameAndOidValue.put(instance.getName() , instance.getOidValue());
        }
        return  mapOfOidNameAndOidValue;
    }



    public String getPayloadForDBNode() throws IOException {

        Gson gson = new Gson();
        return gson.toJson(getOidNameAndOidValue());

    }

    public  void createNodeInDB() throws IOException {
        Neo4jDAO neo4JDAO = new Neo4jDAO();
        neo4JDAO.checkIfNeo4jIsUp(baseUrl);
        StringBuilder sb = new StringBuilder(baseUrl);
        String nodeUrl = String.valueOf(sb.append("/node"));
        String s = neo4JDAO.create(nodeUrl, String.valueOf(getPayloadForDBNode()));
    }
}

