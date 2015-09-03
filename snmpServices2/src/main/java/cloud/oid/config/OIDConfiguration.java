package cloud.oid.config;

import java.util.Map;

/**
 * Created by swathi on 8/12/2015.
 */
public class OIDConfiguration {

    private String System;
    private Map<String, String> SystemSubtree;
    private String Interface;
    private String ifNumber;
    private Map<String, String> InterfaceSubtree;

    public String getSystem() {
        return System;
    }

    public void setSystem(String system) {
        System = system;
    }

    public Map<String, String> getSystemSubtree() {
        return SystemSubtree;
    }

    public void setSystemSubtree(Map<String, String> systemSubtree) {
        SystemSubtree = systemSubtree;
    }

    public String getInterface() {
        return Interface;
    }

    public void setInterface(String anInterface) {
        Interface = anInterface;
    }

    public String getIfNumber() {
        return ifNumber;
    }

    public void setIfNumber(String ifNumber) {
        this.ifNumber = ifNumber;
    }

    public Map<String, String> getInterfaceSubtree() {
        return InterfaceSubtree;
    }

    public void setInterfaceSubtree(Map<String, String> interfaceSubtree) {
        InterfaceSubtree = interfaceSubtree;
    }
}
