/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.core.services;

import javax.jws.WebService;

/**
 *
 * @author Emerio
 */
@WebService
public interface ServiceKurir {
    String kurirTransaction(Integer noSimulasi, String idTransaksiBank);
}
