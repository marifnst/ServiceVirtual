/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.virtual.simulator;

import com.core.services.ServiceBank;
import com.virtual.services.ServiceValidation;
import com.virtual.util.UtilService;
import java.util.Map;
import java.util.UUID;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.soap.SOAPBinding;

/**
 *
 * @author Emerio
 */
public class Simulator {

    private static final QName SERVICE_NAME = new QName("http://services.virtual.com/", "ServiceValidation");
    private static final QName PORT_NAME = new QName("http://services.virtual.com/", "ServiceValidationPort");

//    public static void main(String args[]) throws Exception {
//        Service service = Service.create(SERVICE_NAME);
//        String endpointAddress = "http://localhost:8084/ServiceVirtual/services/validation";
//
//        // Add a port to the Service
//        service.addPort(PORT_NAME, SOAPBinding.SOAP11HTTP_BINDING, endpointAddress);
//
//        ServiceValidation sv = service.getPort(ServiceValidation.class);
//        String threadId = UUID.randomUUID().toString();
//        Integer totalData = 100;
//        
//        SimulatorThread st1 = new SimulatorThread(threadId, 1, totalData);
//        SimulatorThread st2 = new SimulatorThread(threadId, 2, totalData);
//        SimulatorThread st3 = new SimulatorThread(threadId, 3, totalData);
//        SimulatorThread st4 = new SimulatorThread(threadId, 4, totalData);
//        st1.start();
//        st2.start();
//        st3.start();
//        st4.start();
//        
////        System.out.println(sv.validation(threadId, 1));
////        System.out.println(sv.validation(threadId, 2));
////        System.out.println(sv.validation(threadId, 3));
////        System.out.println(sv.validation(threadId, 4));
//    }
//    public static void main(String args[]) throws Exception {
//        UtilService us = new UtilService();
//        us.openService();
//        ServiceBank sb = us.getService().getPort(ServiceBank.class);
//        System.out.println(sb.bankTransaction(1, "jnsjns", "vaa"));
//    }
    public static void main(String args[]) throws Exception {
        String threadId = UUID.randomUUID().toString();
        Integer totalData = 100;

        SimulatorThread st1 = new SimulatorThread(threadId, 1, totalData);
        SimulatorThread st2 = new SimulatorThread(threadId, 2, totalData);
        SimulatorThread st3 = new SimulatorThread(threadId, 3, totalData);
        SimulatorThread st4 = new SimulatorThread(threadId, 4, totalData);
        st1.start();
        st2.start();
        st3.start();
        st4.start();

    }
}
