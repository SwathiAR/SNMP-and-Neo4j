package com.torana.training.neo4jDAO;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by swathi on 7/29/2015.
 */
public class Neo4jDatabaseOperations {

    @Autowired
    Neo4jDAO neo4jDAO;

    @Autowired
    JsonParser parser;
    Logger logger = LoggerFactory.getLogger(Neo4jDatabaseOperations.class);


    public void checkIfNeo4jIsUp(String baseUri) {
        WebTarget webTarget = neo4jDAO.getWebTarget(baseUri);
        Response response = null;
        try {
            response = webTarget.request(MediaType.APPLICATION_JSON_TYPE).accept(MediaType.APPLICATION_JSON_TYPE).
                    get();
            logger.info("Database is running");
        } catch (RuntimeException r) {
            throw new RuntimeException("Database is down");
        } finally {
            if (response != null) {
                response.close();
            }
        }


    }

    public String checkUserStatus(String userUri) {
        Response response = neo4jDAO.get(userUri);
        String responseInString = response.readEntity(String.class);
        logger.info("Status code {}", response.getStatus());
        logger.info("Details below{} ", response.readEntity(String.class));
        return responseInString;
    }

    public void changePassword(String payload, String userUri) {
        Response response = neo4jDAO.post(userUri + "/password", payload);
        if (response.getStatus() == 200) {
            logger.info("Password changed successfully");
        } else logger.info("Please enter a password which is different from current password");
    }


    public String discoverRestApiOfNeo4j(String baseUri) {
        String responseInString = null;
        try {
            Response response = neo4jDAO.get(baseUri);
            responseInString = response.readEntity(String.class);
            logger.info("Status code {}", response.getStatus());
            logger.info("Below are the REST APIs {}", response.readEntity(String.class));
        }
        catch (RuntimeException r) {
        r.printStackTrace();
        }
        return responseInString;
    }

    public String discoverRestApiAsJsonStrams(String baseUri) {
        WebTarget webTarget = neo4jDAO.getWebTarget(baseUri);
        Response response = webTarget.request(MediaType.APPLICATION_JSON_TYPE).accept(MediaType.APPLICATION_JSON_TYPE).header("X-Stream", "true").get();
        String responseInString = response.readEntity(String.class);
        logger.info(response.readEntity(String.class));
        return responseInString;
    }


    public String createNodeOrRelationship(String uri, String payload) {

        String id = null;
        try {
            Response response = neo4jDAO.post(uri, payload);
            String responseInString = response.readEntity(String.class);
            logger.info("Status code {}", response.getStatus());
            logger.info("Details are :  {}", responseInString);
            JsonElement parse = parser.parse(responseInString);
            id = parse.getAsJsonObject().get("metadata").getAsJsonObject().get("id").getAsString();
            logger.info("id of the created node is {}", id);
        } catch (RuntimeException r) {
            r.printStackTrace();
        }
        return id;
    }

    public String getNode(String uri, String nodeId) {
        String responseInString = null;

        try {


            Response response = neo4jDAO.get(uri + nodeId);
            responseInString = response.readEntity(String.class);
            //logger.info("Status code {}", response.getStatus());
            logger.info("Details are :  {}", responseInString);

        } catch (RuntimeException r) {
            r.printStackTrace();
        }
        return responseInString;

    }

    public void deleteNode(String uri, String nodeId) {

        try {


            Response response = neo4jDAO.delete(uri + nodeId);
            logger.info("Delete on {}", uri);
            logger.info("Status code {}", response.getStatus());

        } catch (RuntimeException r) {
            r.printStackTrace();
        }

    }







}



