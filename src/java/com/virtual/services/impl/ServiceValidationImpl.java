package com.virtual.services.impl;

import com.virtual.engines.Engine1;
import com.virtual.engines.Engine2;
import com.virtual.engines.Engine3;
import com.virtual.engines.Engine4;
import com.virtual.services.ServiceValidation;
import javax.jws.WebService;

@WebService(
        endpointInterface = "com.virtual.services.ServiceValidation",
        serviceName = "ServiceValidation"
)
public class ServiceValidationImpl implements ServiceValidation {

    @Override
    public String validation(String threadId, Integer noSimulasi) {
        String result = "FAILED";
        if (noSimulasi.equals(1)) {
            Engine1 e1 = new Engine1();
            String idTransaksi = e1.process(threadId);
            String idTransaksiBank = e1.transaksiBank(idTransaksi);
            e1.transaksiKurir(idTransaksiBank);
            result = "SUCCESS";
        } else if (noSimulasi.equals(2)) {
            Engine2 e2 = new Engine2();
            String idTransaksi = e2.process(threadId);
            String idTransaksiBank = e2.transaksiBank(idTransaksi);
            result = "SUCCESS";
        } else if (noSimulasi.equals(3)) {
            Engine3 e3 = new Engine3();
            String idTransaksi = e3.process(threadId);
            String idTransaksiBank = e3.transaksiBank(idTransaksi);
            result = e3.transaksiKurir(idTransaksiBank);
        } else if (noSimulasi.equals(4)) {
            Engine4 e4 = new Engine4();
            result = e4.process(threadId);
        }

        return result;
    }

}
