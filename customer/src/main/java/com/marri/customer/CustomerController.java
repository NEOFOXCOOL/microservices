package com.marri.customer;

import lombok.AllArgsConstructor;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/v1/registering")
public class CustomerController {
    private final CustomerServices customerServices;

    @PostMapping
    public void registerCustomer(@RequestBody CustomerRegisterRequest request){
        customerServices.registerCustomer(request);
    }
}
