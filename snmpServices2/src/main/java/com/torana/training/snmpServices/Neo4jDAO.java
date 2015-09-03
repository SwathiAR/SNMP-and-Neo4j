package com.torana.training.snmpServices;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by swathi on 7/8/2015.
 */
public class Neo4jDAO {
    Logger logger = LoggerFactory.getLogger(Neo4jDAO.class);
    JsonParser parser = new JsonParser();


    public void checkIfNeo4jIsUp(String uri) {
        Response response = get(uri);
        if(response==null){
            throw new RuntimeException("Database is down");
        }
    }

    public Response get(String uri) {
        WebTarget webTarget = getWebTarget(uri);
        Response response = null;
        try {
            response = webTarget.request(MediaType.APPLICATION_JSON_TYPE).accept(MediaType.APPLICATION_JSON_TYPE).
                    get();
            logger.info("GET on {}", uri);
            logger.info("Status code {}", response.getStatus());
            logger.info("Returned response {}", response.readEntity(String.class));
            return response;
        } catch (RuntimeException r) {
            r.printStackTrace();
        } finally {
            if (response != null) {
                response.close();
            }
        }
        return response;
    }


    public String create(String uri, String payload) {

        String returnData = null;
        try {
            WebTarget webTarget = getWebTarget(uri);
            Response clientResponse = webTarget.request(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).post(Entity.entity(payload, MediaType.APPLICATION_JSON));
            returnData = clientResponse.readEntity(String.class);
            logger.info("POST on {}", uri);
            logger.info("Status code {}", clientResponse.getStatus());
            logger.info("returned data {}", returnData);
        } catch (RuntimeException r) {
            r.printStackTrace();
        }
        return returnData;
    }



    public String createNode(String uri, String payload) {

        String id = null;
        try {
            String response = create(uri, payload);
            JsonElement parse = parser.parse(response);
            id = parse.getAsJsonObject().get("metadata").getAsJsonObject().get("id").getAsString();

            logger.info("id is {}", id);
        } catch (RuntimeException r) {
            r.printStackTrace();
        }
        return id;
    }

    public void delete(String uri){
        WebTarget webTarget = getWebTarget(uri);
        Response response = webTarget.request(MediaType.APPLICATION_JSON).delete();
        logger.info("Delete on {}", uri);
        logger.info("Status code {}", response.getStatus());

    }




    private WebTarget getWebTarget(String uri) {

        HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("neo4j", "July@2015");
        Client client = ClientBuilder.newClient();
        client.register(feature);
        return client.target(uri);
    }


}

