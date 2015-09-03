package cloud.service;

import cloud.DeviceInfoCollector;
import cloud.domain.*;
import cloud.domain.System;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;


/**
 * Created by swathi on 8/6/2015.
 */
public class ServiceTest {

    SystemService systemService = new SystemService();
    InterfaceService interfaceService = new InterfaceService();
    DeviceInfoCollector deviceInfoCollector = new DeviceInfoCollector("192.168.100.4" , "161" , "public" ,2);


    @Test

    public void shouldCreateNode() throws IOException, IllegalAccessException {

        systemService.createOrUpdate(deviceInfoCollector.setValuesToSystem("Quisk Switch"));

    }


    @Test

    public void shouldGiveAllNodes() {


        Iterable<System> all = systemService.findAll();

        for (System sys : all) {
            java.lang.System.out.println(sys.toString());
        }


    }

    @Test
    public void shouldDeleteSystem() {

        Iterable<System> all = systemService.findAll();

        for (System sys : all) {
            if (sys.getName().equals("Quisk Switch")) {
                interfaceService.delete(sys.getContainedInterface());
                systemService.delete(sys);

            }
        }
    }
}



