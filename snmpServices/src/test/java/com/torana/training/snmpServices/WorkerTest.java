package com.torana.training.snmpServices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Created by swathi on 7/21/2015.
 */
public class WorkerTest {
    Logger logger = LoggerFactory.getLogger(WorkerTest.class);
    Worker worker = new Worker();

    @Test
    public void shouldGiveMapOfOIDNameAndValue() throws IOException {
        logger.info(worker.getPayloadForDBNode());
        worker.createNodeInDB();

    }

}