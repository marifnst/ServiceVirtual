/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.virtual.engines;

import com.core.services.ServiceBank;
import com.core.services.ServiceKurir;
import com.virtual.entities.TblBuku;
import com.virtual.entities.TblTransaksiBank;
import com.virtual.entities.TblTransaksiKurir;
import com.virtual.entities.TblUser;
import com.virtual.entities.TblValidasi;
import com.virtual.util.UtilDatabase;
import com.virtual.util.UtilGeneral;
import com.virtual.util.UtilService;
import java.util.Date;
import java.util.UUID;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.xml.namespace.QName;

/**
 *
 * @author Emerio
 */
public class EngineSingle {

    private static final QName SERVICE_NAME = new QName("http://services.core.com/", "ServiceBank");
    private static final QName PORT_NAME = new QName("http://services.core.com/", "ServiceBankPort");
    private String threadId;
    private int totalData;
    private UtilDatabase localUtilDatabase = null;

    public String process(String nama, String isUserValid, String estimatedTime, String threadId) {
        localUtilDatabase = new UtilDatabase();
        String idValidasi = insertValidasi(nama, isUserValid, estimatedTime, threadId);
        return idValidasi;
    }

    public String processError(String nama, String isUserValid, String estimatedTime, String threadId) {
        localUtilDatabase = new UtilDatabase();
        String idValidasi = insertValidasiError(nama, isUserValid, estimatedTime, threadId);
        return idValidasi;
    }

    public String transaksiBank(String idValidasi, String nama, String noKartuKredit) {
        String result = "FAILED_KARTU_KREDIT";
//        Service service = Service.create(SERVICE_NAME);
//        String endpointAddress = "http://localhost:8084/ServiceCore/services/transaksi_bank";
//        service.addPort(PORT_NAME, SOAPBinding.SOAP11HTTP_BINDING, endpointAddress);

//        int id = UtilGeneral.getInstance().getUserId();
//        String noKartuKredit = getUserCreditCard(id);
//        UtilService us = new UtilService();
//        us.openService();
//        ServiceBank sb = us.getService().getPort(ServiceBank.class);
//        result = sb.bankTransaction(1, nama, noKartuKredit, idValidasi);
        UtilGeneral ug = new UtilGeneral();
        String checkKartuKredit = ug.getNoKartuKreditNew(nama, noKartuKredit);
        //System.out.println(checkKartuKredit);
        if (checkKartuKredit.equalsIgnoreCase("0")) {
//            System.out.println("masuk");
            String idTransaksi = UUID.randomUUID().toString();
            TblTransaksiBank tblTransaksiBank = new TblTransaksiBank();

            tblTransaksiBank.setIdValidasi(idValidasi);
            tblTransaksiBank.setIdTransaksi(idTransaksi);
            tblTransaksiBank.setNoKartuKredit(noKartuKredit);
            tblTransaksiBank.setStatusTransaksi("FAILED_KARTU_KREDIT");
            tblTransaksiBank.setCreatedDate(new Date());

            localUtilDatabase.openConnection();
            EntityManager em = localUtilDatabase.getEntityManager();
            EntityTransaction et = em.getTransaction();
            et.begin();
            em.persist(tblTransaksiBank);
            et.commit();
            localUtilDatabase.getEntityManager().close();
        } else {
            String idTransaksi = UUID.randomUUID().toString();
            TblTransaksiBank tblTransaksiBank = new TblTransaksiBank();

            tblTransaksiBank.setIdValidasi(idValidasi);
            tblTransaksiBank.setIdTransaksi(idTransaksi);
            tblTransaksiBank.setNoKartuKredit(noKartuKredit);
            tblTransaksiBank.setStatusTransaksi("SUCCESS");
            tblTransaksiBank.setCreatedDate(new Date());

            localUtilDatabase.openConnection();
            EntityManager em = localUtilDatabase.getEntityManager();
            EntityTransaction et = em.getTransaction();
            et.begin();
            em.persist(tblTransaksiBank);
            et.commit();
            localUtilDatabase.getEntityManager().close();
            result = idTransaksi;
        }
        return result;
    }

    public String transaksiKurir(String idTransaksiBank, String status) {
        String result = "FAILED";
//        UtilService us = new UtilService();
//        us.openServiceKurir();
//        ServiceKurir sk = us.getService().getPort(ServiceKurir.class);
//        sk.kurirTransaction(1, idTransaksiBank);
//        result = "SUCCESS";

        String idTransaksi = UUID.randomUUID().toString();

        TblTransaksiKurir tblTransaksiKurir = new TblTransaksiKurir();

        tblTransaksiKurir.setIdTransaksi(idTransaksi);
        tblTransaksiKurir.setIdTransaksiBank(idTransaksiBank);
        tblTransaksiKurir.setStatusTransaksi(status);
        tblTransaksiKurir.setCreatedDate(new Date());

        localUtilDatabase.openConnection();
        EntityManager em = localUtilDatabase.getEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.persist(tblTransaksiKurir);
        et.commit();
        localUtilDatabase.getEntityManager().close();

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

    public String insertValidasi(String nama, String isUserValid, String estimatedTime, String threadId) {
        String idValidasi = UUID.randomUUID().toString();

        TblValidasi tblValidasi = new TblValidasi();
        tblValidasi.setIdThread(threadId);
        tblValidasi.setIdValidasi(idValidasi);
        tblValidasi.setNama(nama);
        tblValidasi.setIsUserValid(isUserValid);
        tblValidasi.setElapsedTimeValidation(estimatedTime);
        tblValidasi.setStatusTransaksi("SUCCESS");
        tblValidasi.setCreatedDate(new Date());

        localUtilDatabase.openConnection();
        EntityManager em = localUtilDatabase.getEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.persist(tblValidasi);
        et.commit();
        localUtilDatabase.getEntityManager().close();

        return idValidasi;
    }

    public String insertValidasiError(String nama, String isUserValid, String estimatedTime, String threadId) {
        String idValidasi = UUID.randomUUID().toString();

        TblValidasi tblValidasi = new TblValidasi();
        tblValidasi.setIdThread(threadId);
        tblValidasi.setIdValidasi(idValidasi);
        tblValidasi.setNama(nama);
        tblValidasi.setIsUserValid(isUserValid);
        tblValidasi.setElapsedTimeValidation(estimatedTime);
        tblValidasi.setStatusTransaksi("INVALID_PASSWORD");
        tblValidasi.setCreatedDate(new Date());

        localUtilDatabase.openConnection();
        EntityManager em = localUtilDatabase.getEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.persist(tblValidasi);
        et.commit();
        localUtilDatabase.getEntityManager().close();

        return idValidasi;
    }

    public Integer getStokBuku(int id) {
        localUtilDatabase = new UtilDatabase();
        localUtilDatabase.openConnection();

        EntityManager em = localUtilDatabase.getEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        TblBuku tblBukuResult = em.find(TblBuku.class, id);
        et.commit();
        localUtilDatabase.getEntityManager().close();

        return tblBukuResult.getStok();
    }

    public Integer updateStok(String id) {
        int result = 0;
        UtilGeneral ug = new UtilGeneral();
        ug.updateStokBuku(id);
        return result;
    }

//    public static void main(String args[]) {
//        Engine1 e1 = new Engine1();
//        e1.process("asasa");
//    }
}
