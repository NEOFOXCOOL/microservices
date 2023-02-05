package com.marri.customer;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class CustomerServices {
    private final CustomerRepository customerRepository;
    private final RestTemplate restTemplate;

    public void registerCustomer(CustomerRegisterRequest request){
        // Todo : check if email is valid
        // Todo : Check if email note taken
        // Todo : store customer in DB
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();
        if(customerRepository.findCustomerByEmail(request.email()).isEmpty()){
           customerRepository.saveAndFlush(customer);
        }

        FraudCheckResponce fraudCheckResponce = restTemplate.getForObject(
                "http://localhost:8081/api/v1/fraud-check/{customerid}",
                FraudCheckResponce.class,
                customer.getId()
        );

        if(fraudCheckResponce.isFraudulent()){
            throw new IllegalStateException("isfraudster");
        }
    }

}
