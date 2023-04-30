package com.marri.customer;

import lombok.AllArgsConstructor;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/v1/registering")
public class CustomerController {
    private final CustomerServices customerServices;

    @PostMapping(path = "addcustomer")
    public void registerCustomer(@RequestBody CustomerRegisterRequest request){
        customerServices.registerCustomer(request);
    }

    @GetMapping(path = "listCustomers")
    public List<Customer> allCustomers(){
        return customerServices.showCustomers();
    }
}
