package cloud.oid.config;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

import static org.testng.Assert.*;

/**
 * Created by swathi on 8/12/2015.
 */
public class OidLoaderTest {

    OidLoader oidLoader = new OidLoader();

    @Test
    public void getOids(){
        OIDConfiguration oidConfiguration = oidLoader.getOidConfiguration();

        Assert.assertEquals(oidConfiguration.getSystem() ,".1.3.6.1.2.1.1" );
       Assert.assertEquals(oidConfiguration.getIfNumber() ,".1.3.6.1.2.1.2.1" );
        Assert.assertEquals(oidConfiguration.getInterface() ,".1.3.6.1.2.1.2" );
        Map<String, String> systemSubtree = oidConfiguration.getSystemSubtree();
       Assert.assertEquals(systemSubtree.get("sysDescr") , ".1.3.6.1.2.1.1.1.0");
        Assert.assertEquals(systemSubtree.get("sysObjectID") , ".1.3.6.1.2.1.1.2.0");
        Map<String, String> interfaceSubtree = oidConfiguration.getInterfaceSubtree();
        Assert.assertEquals(interfaceSubtree.get("ifMtu") , ".1.3.6.1.2.1.2.2.1.4");
        Assert.assertEquals(interfaceSubtree.get("ifIndex") , ".1.3.6.1.2.1.2.2.1.1");


    }

}