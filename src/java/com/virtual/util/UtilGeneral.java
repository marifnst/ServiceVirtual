/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.virtual.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Emerio
 */
public class UtilGeneral {

    private static UtilGeneral utilGeneral;

    public static UtilGeneral getInstance() {
        if (utilGeneral == null) {
            utilGeneral = new UtilGeneral();
        }
        return utilGeneral;
    }

    public int getUserId() {
        int result = (int) (Math.random() * 1000);
        return result == 0 ? 1 : result;
    }

    public String getRandomUser() {
        String result = "";
        Random r = new Random();
        int low = 1;
        int high = 1000;
        int resultUser = r.nextInt(high - low) + low;
        result = "NAMA_" + String.valueOf(resultUser);
        return result;
    }

    public String getUserPassword(String username) {
        String result = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            String password = "password_" + username.split("\\_")[1];
            md.update(password.getBytes());
            byte byteData[] = md.digest();

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
//            System.out.println(password);
//            result = password.getBytes().toString();
            result = sb.toString();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UtilGeneral.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public String getNoKartuKredit(String user) {
        String query = "SELECT NO_KARTU_KREDIT IS_AVAILABLE FROM TBL_USER WHERE NAMA = '" + user + "'";
        UtilDatabase utilDatabase = new UtilDatabase();
        utilDatabase.openConnectionNative();
        String result = utilDatabase.executeQuery(query);
        utilDatabase.closeConnection();
        return result;
    }

    public String checkUser(String user, String password) {
        String query = "SELECT COUNT(1) IS_AVAILABLE FROM TBL_USER WHERE NAMA = '" + user + "' AND PASSWORD = '" + password + "'";
        UtilDatabase utilDatabase = new UtilDatabase();
        utilDatabase.openConnectionNative();
        String result = utilDatabase.executeQuery(query);
        utilDatabase.closeConnection();
        return result;
    }

    public void setElapsedTimeValidasi(String validationId, String elapsedTime) {
        String query = "UPDATE TBL_VALIDASI SET ELAPSED_TIME_BANK = '" + elapsedTime + "' WHERE ID_VALIDASI ='" + validationId + "'";
        UtilDatabase utilDatabase = new UtilDatabase();
        utilDatabase.openConnectionNative();
        utilDatabase.executeUpdate(query);
        utilDatabase.closeConnection();
    }

    public void setElapsedTimeBank(String validationId, String elapsedTime) {
        String query = "UPDATE TBL_VALIDASI SET ELAPSED_TIME_BANK = '" + elapsedTime + "' WHERE ID_VALIDASI ='" + validationId + "'";
        UtilDatabase utilDatabase = new UtilDatabase();
        utilDatabase.openConnectionNative();
        utilDatabase.executeUpdate(query);
        utilDatabase.closeConnection();
    }

    public void updateStokBuku(String idBuku) {
        String query = "UPDATE TBL_BUKU SET STOK = STOK - 1 WHERE ID ='" + idBuku + "'";
        UtilDatabase utilDatabase = new UtilDatabase();
        utilDatabase.openConnectionNative();
        utilDatabase.executeUpdate(query);
        utilDatabase.closeConnection();
    }

    public static void main(String args[]) {
//        UtilGeneral.getInstance().setElapsedTimeBank("ad307d31-5998-410b-a6e2-026b42f4d788", "asasa");
//        System.out.println(UtilGeneral.getInstance().checkUser("NAMA_1", "[B@6c0267a"));
//        System.out.println(UtilGeneral.getInstance().checkUser("NAMA_1", "[B@6c0"));
        System.out.println(UtilGeneral.getInstance().getNoKartuKredit("NAMA_1"));
    }
}
