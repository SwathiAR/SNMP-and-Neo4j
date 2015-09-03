package cloud;

import cloud.domain.Interface;
import cloud.domain.System;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.Set;

/**
 * Created by swathi on 8/13/2015.
 */
public class DeviceInfoCollectorTest {

    DeviceInfoCollector deviceInfoCollector = new DeviceInfoCollector("192.168.100.4" ,"161" ,"public" ,2);


    @Test
    public void shouldSetTheSystemValues() throws IOException, IllegalAccessException {
        System system = deviceInfoCollector.setValuesToSystem("Quisk Switch");
        Assert.assertEquals(system.getName() , "Quisk Switch");
        Assert.assertEquals(system.getSysDescr(), "test environment switch junos");
        Assert.assertEquals(system.getSysContact(), "Ryan");
        Assert.assertEquals(system.getSysLocation(), "sanjose");
        Assert.assertEquals(system.getSysObjectID(), "1.3.6.1.4.1.2636.1.1.1.2.43");


        Set<Interface> containedInterface = system.getContainedInterface();
        Assert.assertEquals(containedInterface.size(), 113);
        Interface dsc = giveInteraceWithGivenDescr(containedInterface, "dsc");
        Assert.assertEquals(dsc.getIfDescr(), "dsc");
        Assert.assertEquals(dsc.getIfType(), "1");
        Assert.assertEquals(dsc.getIfIndex(), "5");
        Assert.assertEquals(dsc.getIfMtu(), "2147483647");
        Assert.assertEquals(dsc.getIfSpeed(), "0");
        Assert.assertEquals(dsc.getIfPhysAddress(), "");
        Assert.assertEquals(dsc.getIfAdminStatus(), "1");
        Assert.assertEquals(dsc.getIfOperStatus(), "1");
        Assert.assertEquals(dsc.getIfLastChange(), "0:00:00.00");
        Assert.assertEquals(dsc.getIfInOctets(), "0");
        Assert.assertEquals(dsc.getIfInUcastPkts(), "0");
        Assert.assertEquals(dsc.getIfInNUcastPkts(), "0");
        Assert.assertEquals(dsc.getIfInDiscards(), "0");
        Assert.assertEquals(dsc.getIfInErrors(), "0");
        Assert.assertEquals(dsc.getIfInUnknownProtos(), "0");
        Assert.assertEquals(dsc.getIfOutDiscards(), "0");
        Assert.assertEquals(dsc.getIfOutErrors(), "0");
        Assert.assertEquals(dsc.getIfOutNUcastPkts(), "0");
        Assert.assertEquals(dsc.getIfOutOctets(), "0");
        Assert.assertEquals(dsc.getIfOutQLen(), "0");
        Assert.assertEquals(dsc.getIfOutUcastPkts(), "0");
        Assert.assertEquals(dsc.getIfSpecific(), "0.0");
        Assert.assertEquals(dsc.getName() , "Quisk Switch-dsc");


    }

    public Interface giveInteraceWithGivenDescr(Set<Interface> containedInterfac, String desc) {
        for (Interface intrface : containedInterfac) {
            if (desc.equals(intrface.getIfDescr())) {
                return intrface;
            }

        }
        return null;

    }
}

