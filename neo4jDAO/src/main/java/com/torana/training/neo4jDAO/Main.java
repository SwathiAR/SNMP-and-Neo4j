    package com.torana.training.neo4jDAO;


    /**
     * Created by swathi on 7/8/2015.
     */
    public class Main {
        static final  String baseUrl = "http://localhost:7474/db/data";
        public  void main(String[] args) {

            Neo4jDAO neo4JDAO = new Neo4jDAO();
            //neo4JDAO.checkIfNeo4jIsUp(baseUrl);
           // neo4JDAO.get(baseUrl);
            neo4JDAO.post(baseUrl + "/ext/GetAll/graphdb/get_all_nodes",null);

           /* StringBuilder sb = new StringBuilder(baseUrl);
            String nodePayload = "{\"name\" : \"Swathi\"}";
            String nodeUrl = String.valueOf(sb.append("/node"));
            String nodeId = neo4JDAO.createNode(nodeUrl, nodePayload);

            StringBuilder sb2 = new StringBuilder(baseUrl);
            String labelPayload = "\"Person\"";
            String labelUrl = String.valueOf(sb2.append("/node/").append(nodeId).append("/labels"));
            neo4JDAO.post(labelUrl , labelPayload);

            StringBuilder sb3 = new StringBuilder(baseUrl);
            String deleteNodeUrl = String.valueOf(sb3.append("/node/").append(2));
            neo4JDAO.delete(deleteNodeUrl);
    
    //        StringBuilder sb3 = new StringBuilder(baseUrl);
    //        String relationshipPayload = "{\"to\" : \"http://localhost:7474/db/data/node/1\",\"type\" : \"KNOWS\"}";
    //        String relationshipUrl = String.valueOf(sb3.append("/node/").append(0).append("/relationships"));
    //        neo4JDAO.post(relationshipUrl , relationshipPayload);*/










        }
    }
