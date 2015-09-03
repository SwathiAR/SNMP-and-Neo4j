package com.torana.training.neo4jDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

/**
 * Created by swathi on 7/29/2015.
 */
@ContextConfiguration("/spring.xml")
public class Neo4jDatabaseOperationsTest extends AbstractTestNGSpringContextTests {

    String userUri = "http://localhost:7474/user/neo4j";
    String baseUri = "http://localhost:7474/db/data";

    @Autowired
    private Neo4jDatabaseOperations neo4jDatabaseOperations;


    @Test
    public void checkifNeo4jIsRunning() {

        neo4jDatabaseOperations.checkIfNeo4jIsUp(baseUri);
    }

    @Test
    public void checkIfPasswordChangeIsRequired() {
        neo4jDatabaseOperations.checkUserStatus(userUri);
    }

    @Test
    public void checkIfPasswordChangeWorks() {
        String payload = "{\"password\" : \"July@2015\"}";
        neo4jDatabaseOperations.changePassword(payload, userUri);
    }


    @Test
    public void shouldGiveAllRestApi() {
        neo4jDatabaseOperations.discoverRestApiOfNeo4j(baseUri);
    }


    @Test
    public void shouldGiveAllRestApiAsJsonStream() {
        neo4jDatabaseOperations.discoverRestApiAsJsonStrams(baseUri);
    }

    @Test
    public void shouldCreateNode() {
        String payload = "{\"device\" : \"Switch\"}";
        neo4jDatabaseOperations.createNodeOrRelationship(baseUri + "/node", payload);
    }

    @Test
    public void shouldgetGivenNode() {
        neo4jDatabaseOperations.getNode(baseUri + "/node/", "8");

    }


    @Test
    public void shouldDeleteTheGivenNode() {
        neo4jDatabaseOperations.deleteNode(baseUri + "/node/", "2");

    }

    @Test
    public void shouldCreateRelationship() {

        String payload = "{\n" +
                "  \"to\" : \"http://localhost:7474/db/data/node/9\",\n" +
                "  \"type\" : \"Knows\",\n" +
                "  \"data\" : {\n" +
                "    \"since\" : \"2014\"\n" +
                "  }\n" +
                "}";
        neo4jDatabaseOperations.createNodeOrRelationship(baseUri + "/node/8/relationships", payload);
    }



}