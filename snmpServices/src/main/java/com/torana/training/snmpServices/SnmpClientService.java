package com.torana.training.snmpServices;

import org.snmp4j.*;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.*;
import org.snmp4j.transport.DefaultUdpTransportMapping;

import java.io.IOException;


/**
 * Created by swathi on 7/14/2015.
 */
public class SnmpClientService {

    String address;
    Snmp snmp;

    public SnmpClientService(String address){
        super();
        this.address=address;

        try {
            start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void close() throws IOException {
        snmp.close();
    }

    public void start() throws IOException {
        TransportMapping transport = new DefaultUdpTransportMapping();
        snmp = new Snmp(transport);
        transport.listen();
    }
    public String getAsString(OID oid) throws IOException {
        ResponseEvent event =  get(new OID[] {oid});

        return event.getResponse().get(0).getVariable().toString();
    }

    private ResponseEvent get(OID[] oids) throws IOException {
        ResponseEvent event = snmp.send(getPDU(oids), getTarget( ) , null);
        if (event != null && event.getResponse()!=null){
            return  event;
        }
        throw new RuntimeException("GET timed out. It may be caused because of not simulating SNMP agent ");

    }


    public PDU getPDU(OID[] oids){
        PDU pdu = new PDU();
        for (OID oid : oids){
            pdu.add(new VariableBinding(oid));
        }
        pdu.setType(PDU.GET);
        return pdu;

    }

    public Target getTarget(){

        Address targetAddress = GenericAddress.parse(address);
        CommunityTarget target = new CommunityTarget();
        target.setCommunity(new OctetString("public"));
        target.setAddress(targetAddress);
        target.setRetries(2);
        target.setTimeout(1500);
        target.setVersion(SnmpConstants.version2c);
        return target;
    }


}
