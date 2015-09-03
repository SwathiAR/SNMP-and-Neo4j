package cloud;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;

import cloud.domain.Interface;
import cloud.domain.System;
import cloud.oid.config.OIDConfiguration;
import cloud.oid.config.OidLoader;
import org.snmp4j.smi.OID;

/**
 * Created by swathi on 8/13/2015.
 */
public class DeviceInfoCollector {

    SnmpClientService snmpClientService;

    public DeviceInfoCollector(String ipAddr , String snmpPort , String community , int snmpVersion) {

        snmpClientService = new SnmpClientService("udp:" + ipAddr +  "/" + snmpPort , community , snmpVersion);
    }

    public System setValuesToSystem(String name) throws IllegalAccessException, IOException {
        System aSystem = new System();
        aSystem.setName(name);

        Class<? extends System> systemCls = aSystem.getClass();
        OidLoader oidLoader = new OidLoader();
        OIDConfiguration oidConfiguration = oidLoader.getOidConfiguration();
        for (String key : oidConfiguration.getSystemSubtree().keySet()) {
            Field field = getField(systemCls, key);
            if (field != null) {
                String oid = oidConfiguration.getSystemSubtree().get(key);
                String oidValue = snmpClientService.getAsString(new OID(oid));
                field.set(aSystem, oidValue);
            }
        }
        aSystem.setContainedInterface(getInterfaces(oidConfiguration.getInterfaceSubtree(), aSystem));
        return aSystem;
    }

    private Set<Interface> getInterfaces(Map<String, String> interfaceSubtree, System aSystem) throws IllegalAccessException {
        List<Interface> interfaces = new ArrayList<Interface>();
        for (String key : interfaceSubtree.keySet()) {
            int interfaceIndex = 0;
            String oid = interfaceSubtree.get(key);
            List<String> subTreeAsString = snmpClientService.getSubTreeAsString(oid);
            for (String s : subTreeAsString) {
                if (interfaces.size() <= interfaceIndex) {
                    Interface anInterface = new Interface();
                    anInterface.setSystem(aSystem);
                    interfaces.add(anInterface);
                }
                Field field = getField(Interface.class, key);
                if (field != null) {
                    field.set(interfaces.get(interfaceIndex), s);
                }
                interfaceIndex++;
            }
        }
        for (Interface anInterface : interfaces) {
            anInterface.setName();
        }
        return new HashSet<Interface>(interfaces);
    }


    private Field getField(Class<? extends Object> aClass, String key) {
        try {
            Field declaredField = aClass.getDeclaredField(key);
            declaredField.setAccessible(true);
            return declaredField;
        } catch (NoSuchFieldException e) {
            return null;
        }
    }


}
