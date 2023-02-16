package com.marri.fraud;

import com.ctc.wstx.shaded.msv_core.driver.textui.Debug;
import com.marri.client.fraud.FraudCheckResponce;
import com.marri.client.fraud.FraudClient;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/fraud-check")
@AllArgsConstructor
public class FraudController implements FraudClient {
    private final FraudServices fraudServices;

    @GetMapping(path = "/{customerId}")
    public FraudCheckResponce isFraudster(@PathVariable("customerId") Long customerId) {
        boolean isFraudulentCustomer = fraudServices.isFraudulentCustomer(customerId);
        System.out.println("fraud linked to customer id : " + customerId);
        return new FraudCheckResponce(isFraudulentCustomer);
    }
}
