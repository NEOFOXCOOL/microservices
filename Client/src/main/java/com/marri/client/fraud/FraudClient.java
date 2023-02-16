package com.marri.client.fraud;


public interface FraudClient {
    @GetMapping(path = "/{customerid}")
    public FraudCheckResponce isFraudster(@PathVariable("customerid") Long customerId);
}
