package com.marri.client.fraud;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(
        name = "fraud",
        url = "${client.fraud.url}"
)
public interface FraudClient {
    @GetMapping(path = "/api/v1/fraud-check/{customerid}")
    public FraudCheckResponce isFraudster(@PathVariable("customerid") Long customerId);
}
