
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.snmp4j.PDU;
import org.snmp4j.Target;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.smi.OID;
import org.testng.annotations.Test;

import java.io.IOException;


import static org.testng.Assert.*;

/**
 * Created by swathi on 7/14/2015.
 */
public class SnmpClientServiceTest {

    Logger logger = LoggerFactory.getLogger(SnmpClientServiceTest.class);


    SnmpClientService snmpClientService = new SnmpClientService("udp:192.168.1.64/161");

    /*@Test

    public void shouldGetOID() throws IOException {
        String response = snmpClientService.getAsString(new OID(".1.3.6.1.2.1.1"));
        logger.info("response is {}", response);
        //ResponseEvent responseEvent = snmpClientService.get(new OID[]{new OID(".1.3.6.1.2.1.1.1.0")});
        //logger.info(responseEvent.getResponse().getVariableBindings().toString());
        //logger.info(response.toString());*/

@Test
    public void shouldGetOID() throws IOException {
        snmpClientService.getBulkAsString();



    }
}