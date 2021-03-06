/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.virtual.engines;

import com.core.services.ServiceBank;
import com.core.services.ServiceKurir;
import com.virtual.entities.TblUser;
import com.virtual.entities.TblValidasi;
import com.virtual.services.ServiceValidation;
import com.virtual.util.UtilDatabase;
import com.virtual.util.UtilGeneral;
import com.virtual.util.UtilService;
import java.util.Date;
import java.util.UUID;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.soap.SOAPBinding;

/**
 *
 * @author Emerio
 */
public class Engine1 {

    private static final QName SERVICE_NAME = new QName("http://services.core.com/", "ServiceBank");
    private static final QName PORT_NAME = new QName("http://services.core.com/", "ServiceBankPort");
    private String threadId;
    private int totalData;
    private UtilDatabase localUtilDatabase;

    public String process(String nama, String isUserValid, String estimatedTime, String threadId, Integer noSimulasi) {
        localUtilDatabase = new UtilDatabase();
        String idValidasi = insertValidasi(nama, isUserValid, estimatedTime, threadId, noSimulasi);
        return idValidasi;
    }

    public String transaksiBank(String idValidasi, String nama, String noKartuKredit) {
        String result = "FAILED";
//        Service service = Service.create(SERVICE_NAME);
//        String endpointAddress = "http://localhost:8084/ServiceCore/services/transaksi_bank";
//        service.addPort(PORT_NAME, SOAPBinding.SOAP11HTTP_BINDING, endpointAddress);

//        int id = UtilGeneral.getInstance().getUserId();
//        String noKartuKredit = getUserCreditCard(id);
        
        UtilService us = new UtilService();
        us.openService();
        ServiceBank sb = us.getService().getPort(ServiceBank.class);
        result = sb.bankTransaction(1, nama, noKartuKredit, idValidasi);
        return result;
    }

    public String transaksiKurir(String idTransaksiBank) {
        String result = "FAILED";
        UtilService us = new UtilService();
        us.openServiceKurir();
        ServiceKurir sk = us.getService().getPort(ServiceKurir.class);
        sk.kurirTransaction(1, idTransaksiBank);
        result = "SUCCESS";
        return result;
    }

    public String getUserCreditCard(int id) {
        localUtilDatabase.openConnection();

        EntityManager em = localUtilDatabase.getEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        TblUser tblUserResult = em.find(TblUser.class, id);
        et.commit();
        localUtilDatabase.getEntityManager().close();
        return tblUserResult.getNoKartuKredit();
    }

    public String insertValidasi(String nama, String isUserValid, String estimatedTime, String threadId, Integer noSimulasi) {
        String idValidasi = UUID.randomUUID().toString();

        TblValidasi tblValidasi = new TblValidasi();
        tblValidasi.setIdThread(threadId);
        tblValidasi.setIdValidasi(idValidasi);
        tblValidasi.setNama(nama);
        tblValidasi.setIsUserValid(isUserValid);
        tblValidasi.setElapsedTimeValidation(estimatedTime);
        tblValidasi.setStatusTransaksi("SUCCESS");
        tblValidasi.setCreatedDate(new Date());
        tblValidasi.setNoSimulasi(noSimulasi);

        localUtilDatabase.openConnection();
        EntityManager em = localUtilDatabase.getEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.persist(tblValidasi);
        et.commit();
        localUtilDatabase.getEntityManager().close();

        return idValidasi;
    }
    
//    public static void main(String args[]) {
//        Engine1 e1 = new Engine1();
//        e1.process("asasa");
//    }

}
