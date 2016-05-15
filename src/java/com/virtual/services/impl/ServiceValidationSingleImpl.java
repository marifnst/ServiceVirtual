package com.virtual.services.impl;

import com.virtual.engines.Engine1;
import com.virtual.engines.Engine2;
import com.virtual.engines.Engine3;
import com.virtual.engines.Engine4;
import com.virtual.engines.EngineReal;
import com.virtual.engines.EngineSingle;
import com.virtual.services.ServiceValidationSingle;
import com.virtual.util.UtilGeneral;
import javax.jws.WebService;

@WebService(
        endpointInterface = "com.virtual.services.ServiceValidationSingle",
        serviceName = "ServiceValidationSingle"
)
public class ServiceValidationSingleImpl implements ServiceValidationSingle {

    @Override
    public String validation(String threadId, Integer noSimulasi, String username, String password, String noKartuKredit) {
        String result = "FAILED";
        long startTime = 0;
        long estimatedTime = 0;
        EngineSingle e1 = new EngineSingle();
        UtilGeneral ug = new UtilGeneral();
        String nama = username;

        // hitung elapsed time validasi
        startTime = System.nanoTime();
        String isUserValid = ug.checkUser(nama, password);
        if (isUserValid.equals("1")) {
            estimatedTime = System.nanoTime() - startTime;
            String idTransaksi = e1.process(nama, isUserValid, String.valueOf(estimatedTime), threadId);
            // selesai hitung elapsed time validasi

            // hitung elapsed time dengan bank
            startTime = System.nanoTime();
            String idTransaksiBank = e1.transaksiBank(idTransaksi, nama, noKartuKredit);
            estimatedTime = System.nanoTime() - startTime;
            ug.setElapsedTimeBank(idTransaksi, String.valueOf(estimatedTime));
            // selesai hitung elapsed time dengan bank

            if (!idTransaksiBank.equalsIgnoreCase("FAILED_KARTU_KREDIT")) {
                // hitung elapsed time dengan kurir
                startTime = System.nanoTime();
                if (noSimulasi == 4) {
                    e1.transaksiKurir(idTransaksiBank, "VIRTUAL_LOST_CONNECTION");
                } else {
                    e1.transaksiKurir(idTransaksiBank, "SUCCESS");
                }
                estimatedTime = System.nanoTime() - startTime;
                ug.setElapsedTimeKurir(idTransaksi, String.valueOf(estimatedTime));
                result = "SUCCESS";
            } else {
                result = "Invalid Kartu Kredit";
            }
        } else {
            estimatedTime = System.nanoTime() - startTime;
            String idTransaksi = e1.processError(nama, isUserValid, String.valueOf(estimatedTime), threadId);
            // selesai hitung elapsed time validasi
        }
        return result;
    }

    public static void main(String args[]) {
        ServiceValidationSingleImpl svi = new ServiceValidationSingleImpl();
        String password = "password_1";
//        svi.validation("UNIT_TEST_1", 1, "NAMA_1", UtilGeneral.getInstance().getUserPassword(password), "123456789_1");
//        svi.validation("UNIT_TEST_2", 1, "NAMA_1", UtilGeneral.getInstance().getUserPassword(password), "123456789");
        svi.validation("UNIT_TEST_4", 4, "NAMA_1", UtilGeneral.getInstance().getUserPassword(password), "123456789_1");
//        svi.validation("UNIT_TEST_2", 2);
//        svi.validation("UNIT_TEST_3", 3);
//        svi.validation("UNIT_TEST_4", 4);
    }
}
