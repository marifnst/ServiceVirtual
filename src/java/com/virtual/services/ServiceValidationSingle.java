package com.virtual.services;

import javax.jws.WebService;

@WebService
public interface ServiceValidationSingle {

    String validation(String threadId, Integer noSimulasi, String username, String password, String noKartuKredit);
}
