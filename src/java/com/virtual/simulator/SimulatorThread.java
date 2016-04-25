/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.virtual.simulator;

import com.virtual.services.ServiceValidation;
import com.virtual.util.UtilService;

/**
 *
 * @author Emerio
 */
public class SimulatorThread extends Thread {

    String threadId;
    Integer noSimulasi;
    Integer totalData;

    public String getThreadId() {
        return threadId;
    }

    public void setThreadId(String threadId) {
        this.threadId = threadId;
    }

    public Integer getNoSimulasi() {
        return noSimulasi;
    }

    public void setNoSimulasi(Integer noSimulasi) {
        this.noSimulasi = noSimulasi;
    }

    public Integer getTotalData() {
        return totalData;
    }

    public void setTotalData(Integer totalData) {
        this.totalData = totalData;
    }

    public SimulatorThread(String threadId, Integer noSimulasi, Integer totalData) {
        this.threadId = threadId;
        this.noSimulasi = noSimulasi;
        this.totalData = totalData;
    }

    @Override
    public void run() {
        UtilService us = new UtilService();
        us.openService();
        ServiceValidation sv = us.getService().getPort(ServiceValidation.class);
        
        for (int i = 0; i < totalData / 4; i++) {
            sv.validation(threadId, noSimulasi);
        }
    }
}
