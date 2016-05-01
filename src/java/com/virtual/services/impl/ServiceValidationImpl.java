package com.virtual.services.impl;

import com.virtual.engines.Engine1;
import com.virtual.engines.Engine2;
import com.virtual.engines.Engine3;
import com.virtual.engines.Engine4;
import com.virtual.services.ServiceValidation;
import com.virtual.util.UtilGeneral;
import javax.jws.WebService;

@WebService(
        endpointInterface = "com.virtual.services.ServiceValidation",
        serviceName = "ServiceValidation"
)
public class ServiceValidationImpl implements ServiceValidation {

    @Override
    public String validation(String threadId, Integer noSimulasi) {
        String result = "FAILED";
        long startTime = 0;
        long estimatedTime = 0;
        try {
            switch (noSimulasi) {
                case 1: {
                    Engine1 e1 = new Engine1();
                    UtilGeneral ug = new UtilGeneral();
                    String nama = ug.getRandomUser();
                    String password = ug.getUserPassword(nama);
                    String noKartuKredit = ug.getNoKartuKredit(nama);
                    
                    // hitung elapsed time validasi
                    startTime = System.nanoTime();
                    String isUserValid = ug.checkUser(nama, password);
                    estimatedTime = System.nanoTime() - startTime;
                    String idTransaksi = e1.process(nama, isUserValid, String.valueOf(estimatedTime), threadId);
                    // selesai hitung elapsed time validasi
                    
                    // hitung elapsed time dengan bank
                    startTime = System.nanoTime();
                    String idTransaksiBank = e1.transaksiBank(idTransaksi, nama, noKartuKredit);
                    estimatedTime = System.nanoTime() - startTime;
                    ug.setElapsedTimeBank(idTransaksi, String.valueOf(estimatedTime));
                    // selesai hitung elapsed time dengan bank
                    
                    e1.transaksiKurir(idTransaksiBank);
                    result = "SUCCESS";
                    break;
                }
                case 2: {
                    Engine2 e2 = new Engine2();
                    UtilGeneral ug = new UtilGeneral();
                    String nama = ug.getRandomUser();
                    String password = ug.getUserPassword(nama);
                    String noKartuKredit = "ERROR_KARTU_KREDIT";
                    
                    // hitung elapsed time validasi
                    startTime = System.nanoTime();
                    String isUserValid = ug.checkUser(nama, password);
                    estimatedTime = System.nanoTime() - startTime;
                    String idTransaksi = e2.process(nama, isUserValid, String.valueOf(estimatedTime), threadId);
                    // selesai hitung elapsed time validasi

                    // hitung elapsed time dengan bank
                    startTime = System.nanoTime();
                    String idTransaksiBank = e2.transaksiBank(idTransaksi, nama, noKartuKredit);
                    estimatedTime = System.nanoTime() - startTime;
                    ug.setElapsedTimeBank(idTransaksi, String.valueOf(estimatedTime));
                    // selesai hitung elapsed time dengan bank
                    result = "SUCCESS";
                    break;
                }
                case 3: {
                    Engine3 e3 = new Engine3();
                    UtilGeneral ug = new UtilGeneral();
                    String nama = ug.getRandomUser();
                    String password = ug.getUserPassword(nama);
                    String noKartuKredit = ug.getNoKartuKredit(nama);

                    // hitung elapsed time validasi
                    startTime = System.nanoTime();
                    String isUserValid = ug.checkUser(nama, password);
                    estimatedTime = System.nanoTime() - startTime;
                    String idTransaksi = e3.process(nama, isUserValid, String.valueOf(estimatedTime), threadId);
                    // selesai hitung elapsed time validasi

                    // hitung elapsed time dengan bank
                    startTime = System.nanoTime();
                    String idTransaksiBank = e3.transaksiBank(idTransaksi, nama, noKartuKredit);
                    estimatedTime = System.nanoTime() - startTime;
                    ug.setElapsedTimeBank(idTransaksi, String.valueOf(estimatedTime));
                    // selesai hitung elapsed time dengan bank
                    
                    result = e3.transaksiKurir(idTransaksiBank);
                    break;
                }
                case 4:
                    Engine4 e4 = new Engine4();
                    UtilGeneral ug = new UtilGeneral();
                    String nama = ug.getRandomUser();

                    // hitung elapsed time validasi
                    startTime = System.nanoTime();
                    String isUserValid = ug.checkUser(nama, "");
                    estimatedTime = System.nanoTime() - startTime;
                    String idTransaksi = e4.process(nama, isUserValid, String.valueOf(estimatedTime), threadId);
                    // selesai hitung elapsed time validasi
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String args[]) {
        ServiceValidationImpl svi = new ServiceValidationImpl();
//        svi.validation("UNIT_TEST_1", 1);
//        svi.validation("UNIT_TEST_2", 2);
//        svi.validation("UNIT_TEST_3", 3);
//        svi.validation("UNIT_TEST_4", 4);
    }
}
