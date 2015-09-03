import org.snmp4j.*;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.*;
import org.snmp4j.transport.DefaultUdpTransportMapping;

import java.io.IOException;
import java.util.Vector;


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

    public ResponseEvent get(OID[] oids) throws IOException {
        ResponseEvent event = snmp.send(getPDU(oids), getTarget( ) , null);
        if (event != null){
         return  event;
        }
        throw new RuntimeException("GET timed out");

    }


    public PDU getPDU(OID[] oids){
        PDU pdu = new PDU();
        for (OID oid : oids){
            pdu.add(new VariableBinding(oid));
        }
        pdu.setType(PDU.GET);
        return pdu;

    }

    public void  getBulkAsString() throws IOException {
        ResponseEvent event =  getBulk();
        PDU response = event.getResponse();


        if (response == null) {
            System.out.println("TimeOut...");
        }
        else {

            if (response.getErrorStatus() == PDU.noError) {

                Vector<? extends VariableBinding> variableBindings = event.getResponse().getVariableBindings();
                for (VariableBinding variableBinding : variableBindings) {
                    System.out.println(variableBinding.getOid().toString()+ " : " + variableBinding.getVariable());

                }
            } else {
                System.out.println("Error:" + response.getErrorStatusText());
            }
        }
    }


    public ResponseEvent getBulk() throws IOException {
        ResponseEvent event = snmp.send(getBulkPDU(), getTarget( ));
        if (event != null){
            return  event;
        }
        throw new RuntimeException("GET timed out");

    }


    public PDU getBulkPDU(){
        PDU pdu = new PDU();
        pdu.setType(PDU.GETBULK);
        pdu.setMaxRepetitions(200);
        pdu.setNonRepeaters(0);
        pdu.add(new VariableBinding(new OID(".1.3.6.1.2.1.1")));
    return pdu;
    }




    public Target getTarget(){

        Address targetAddress = GenericAddress.parse(address);
        CommunityTarget target = new CommunityTarget();
        target.setCommunity(new OctetString("quisk"));
        target.setAddress(targetAddress);
        target.setRetries(2);
        target.setTimeout(1500);
        target.setVersion(SnmpConstants.version2c);
        return target;
    }


}
