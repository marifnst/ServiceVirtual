/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.virtual.util;

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
}
