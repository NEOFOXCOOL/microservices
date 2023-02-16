package com.marri.customer;

import com.marri.client.fraud.FraudCheckResponce;
import com.marri.client.fraud.FraudClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
@Service
@AllArgsConstructor
public class CustomerServices {
    private final CustomerRepository customerRepository;
    private final FraudClient fraudClient;

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

        FraudCheckResponce fraudCheckResponce =
                fraudClient.isFraudster(customer.getId());

        if(fraudCheckResponce.isFraudulent()){
            throw new IllegalStateException("isfraudster");
        }
    }

}
