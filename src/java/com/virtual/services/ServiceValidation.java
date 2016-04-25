package com.virtual.services;

import javax.jws.WebService;

@WebService
public interface ServiceValidation {

    String validation(String threadId, Integer noSimulasi);
}
