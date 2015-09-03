package com.torana.training.snmpServices;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.ws.rs.core.MediaType;


/**
 * Created by swathi on 7/8/2015.
 */
public class Neo4jDAO {
    Logger logger = LoggerFactory.getLogger(Neo4jDAO.class);
    JsonParser parser = new JsonParser();


    public void checkIfNeo4jIsUp(String uri) {
        ClientResponse response = get(uri);
        if(response==null){
            throw new RuntimeException("Database is down");
        }
    }

    public ClientResponse get(String uri) {
        WebResource webResource = getWebResource(uri);
        ClientResponse response = null;
        try {
            response = webResource.accept(MediaType.APPLICATION_JSON_TYPE).type(MediaType.APPLICATION_JSON_TYPE).get(ClientResponse.class);

            logger.info("GET on {}", uri);
            logger.info("Status code {}", response.getStatus());
            logger.info("Returned response {}", response.getEntity(String.class));
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
            WebResource webResource = getWebResource(uri);
            ClientResponse clientResponse = webResource.accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON).post(ClientResponse.class , payload);
            returnData = clientResponse.getEntity(String.class);
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
        WebResource webResource = getWebResource(uri);
        ClientResponse clientResponse = webResource.type(MediaType.APPLICATION_JSON).delete(ClientResponse.class);
        logger.info("Delete on {}", uri);
        logger.info("Status code {}", clientResponse.getStatus());

    }




    private WebResource getWebResource(String uri) {

        //HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("neo4j", "July@2015");
        Client client = Client.create();
        client.addFilter(new HTTPBasicAuthFilter("neo4j","July@2015"));
        return client.resource(uri);
    }

}

