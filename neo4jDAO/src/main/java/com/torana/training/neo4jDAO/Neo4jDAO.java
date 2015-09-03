package com.torana.training.neo4jDAO;

import com.google.gson.JsonParser;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

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

    @Autowired
    JsonParser parser;


    public Response get(String uri) {
        WebTarget webTarget = getWebTarget(uri);
        Response response = null;
        try {
            response = webTarget.request(MediaType.APPLICATION_JSON_TYPE).accept(MediaType.APPLICATION_JSON_TYPE).
                    get();
        } catch (RuntimeException r) {
            logger.info("Database is down");
        }
        return response;
    }


    public Response post(String uri, String payload) {
        WebTarget webTarget = getWebTarget(uri);
        Response response = null;
        try {
            response = webTarget.request(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).post(Entity.entity(payload, MediaType.APPLICATION_JSON));
        } catch (RuntimeException r) {
            r.printStackTrace();
        }
        return response;
    }





    public Response delete(String uri){
        WebTarget webTarget = getWebTarget(uri);
        Response response = null;
        try {
             response =  webTarget.request(MediaType.APPLICATION_JSON).delete();
        } catch (RuntimeException r) {
            r.printStackTrace();
        }
        return response;




    }




    public WebTarget getWebTarget(String uri) {

        HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("neo4j", "July@2015");
        Client client = ClientBuilder.newClient();
        client.register(feature);
        return client.target(uri);
    }


}

