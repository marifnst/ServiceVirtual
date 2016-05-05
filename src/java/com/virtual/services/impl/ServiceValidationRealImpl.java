package com.virtual.services.impl;

import com.virtual.engines.EngineReal;
import com.virtual.services.ServiceValidationReal;
import com.virtual.util.UtilGeneral;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.jws.WebService;

/**
 *
 * @author Emerio
 */
@WebService(
        endpointInterface = "com.virtual.services.ServiceValidationReal",
        serviceName = "ServiceValidationReal"
)
public class ServiceValidationRealImpl implements ServiceValidationReal {

    @Override
    public String validation(String threadId, Integer noSimulasi, String username, String password, String noKartuKredit, String idBuku) {
        long startTime = 0;
        long estimatedTime = 0;
        String result = "FAILED";

        EngineReal e1 = new EngineReal();
        UtilGeneral ug = new UtilGeneral();
        String nama = username;

        // hitung elapsed time validasi
        startTime = System.nanoTime();
        String isUserValid = ug.checkUser(nama, password);
        if (isUserValid.equals("1")) {
            Integer stokBuku = e1.getStokBuku(Integer.parseInt(idBuku));
            if (stokBuku > 0) {
                e1.updateStok(idBuku);
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
            } else {
                result = "Stok Buku Habis";
            }
        } else {
            result = "Invalid Password";
        }
        return result;
    }

    public static void main(String args[]) throws NoSuchAlgorithmException {
        ServiceValidationRealImpl svr = new ServiceValidationRealImpl();
        String password = "password_1";
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        
        byte byteData[] = md.digest();
 
        //convert the byte to hex format method 1
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < byteData.length; i++) {
         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
        
//        System.out.println(password.getBytes().toString());
        String result = svr.validation("", 1, "NAMA_1", sb.toString(), "123456789_1", "5");
//        String result = svr.validation("", 1, "NAMA_1", "error", "123456789_1", "5");
        System.out.println(result);
    }
}
