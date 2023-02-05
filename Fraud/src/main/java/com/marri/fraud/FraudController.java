package com.marri.fraud;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/fraud-check")
@AllArgsConstructor
public class FraudController {
    private final FraudServices fraudServices;

    @GetMapping(path = "/{customerid}")
    public FraudCheckResponce isFraudster(@PathVariable("customerid") Long customerId){
        boolean isFraudulentCustomer = fraudServices.isFraudulentCustomer(customerId);
        return new FraudCheckResponce(isFraudulentCustomer);
    }
}
