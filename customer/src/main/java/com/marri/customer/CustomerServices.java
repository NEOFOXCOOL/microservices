package com.marri.customer;

import com.marri.amqp.RabbitMQMessageProducer;
import com.marri.client.fraud.FraudCheckResponce;
import com.marri.client.fraud.FraudClient;
import com.marri.client.notification.NotificationClient;
import com.marri.client.notification.NotificationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
@Service
@AllArgsConstructor
public class CustomerServices {
    private final CustomerRepository customerRepository;
    private final FraudClient fraudClient;
    private final RabbitMQMessageProducer rabbitMQMessageProducer;
    public void registerCustomer(CustomerRegisterRequest request){

        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();
        if(customerRepository.findCustomerByEmail(request.email()).isEmpty()){
          customerRepository.saveAndFlush(customer);
        }

        NotificationRequest notificationRequest = new NotificationRequest(
                customer.getId(),
                customer.getEmail(),
                "Hello world, massage from customer by marri"
        );

        rabbitMQMessageProducer.publish(
                notificationRequest,
                "internal.exchange",
                "internal.notification.routing-key"
        );

        FraudCheckResponce fraudCheckResponce =
                fraudClient.isFraudster(customer.getId());

        if(fraudCheckResponce.isFraudulent()){
            throw new IllegalStateException("isfraudster");
        }
    }

}
