package com.torana.training.snmpServices;

import com.google.gson.Gson;
import org.snmp4j.smi.OID;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * Created by swathi on 7/20/2015.
 */
public class Worker {
    static final  String baseUrl = "http://localhost:7474/db/data";

    public Map<String,String> getMapOfOidNameAndOidValue() throws IOException {
        YamlReader yamlReader = new YamlReader();
        SnmpClientService snmpClientService = new SnmpClientService("udp:192.168.100.4/161");
        List<Map<String, String>> listOfOIDsAndNames = yamlReader.provideOIDLIst();
        Map<String ,String> mapOfOidNameAndOidValue = new HashMap<String, String>();

        for(Map<String,String> map: listOfOIDsAndNames){
            Set<String> oids = map.keySet();
            for(String oid:oids){
                String oidValue = snmpClientService.getAsString(new OID(oid));
                mapOfOidNameAndOidValue.put(map.get(oid), oidValue);
            }
        }
        return  mapOfOidNameAndOidValue;
    }

    public String getPayloadForDBNode() throws IOException {

        Gson gson = new Gson();
        return gson.toJson(getMapOfOidNameAndOidValue());

    }

    public  void createNodeInDB() throws IOException {
        Neo4jDAO neo4JDAO = new Neo4jDAO();
        neo4JDAO.checkIfNeo4jIsUp(baseUrl);
        StringBuilder sb = new StringBuilder(baseUrl);
        String nodeUrl = String.valueOf(sb.append("/node"));
        String s = neo4JDAO.create(nodeUrl, String.valueOf(getPayloadForDBNode()));
    }
}

