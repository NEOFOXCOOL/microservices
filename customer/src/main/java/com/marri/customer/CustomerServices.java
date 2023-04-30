package com.marri.customer;

import com.marri.amqp.RabbitMQMessageProducer;
import com.marri.client.fraud.FraudCheckResponce;
import com.marri.client.fraud.FraudClient;
import com.marri.client.notification.NotificationClient;
import com.marri.client.notification.NotificationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerServices {
    private final CustomerRepository customerRepository;
    private final FraudClient fraudClient;
    private final RabbitMQMessageProducer rabbitMQMessageProducer;
    public void registerCustomer(CustomerRegisterRequest request){

        //TODO: Build Customer
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();
        //TODO: Save Customer
        if(customerRepository.findCustomerByEmail(request.email()).isEmpty()){
          customerRepository.saveAndFlush(customer);
        }
        //TODO: Generat new Notification
        NotificationRequest notificationRequest = new NotificationRequest(
                customer.getId(),
                customer.getEmail(),
                String.format("Hi %s, Welcome to Marri Application",customer.getFirstName())
        );

        //TODO: send notification to Queue
        rabbitMQMessageProducer.publish(
                notificationRequest,
                "internal.exchange",
                "internal.notification.routing-key"
        );

        //TODO: Check if customer is fraudster
        FraudCheckResponce fraudCheckResponce =
                fraudClient.isFraudster(customer.getId());

        if(fraudCheckResponce.isFraudulent()){
            throw new IllegalStateException("isfraudster");
        }
    }

    public List<Customer> showCustomers(){
        return customerRepository.findAll();
    }

}
