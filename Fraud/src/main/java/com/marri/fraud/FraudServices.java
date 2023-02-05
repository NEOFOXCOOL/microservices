package com.marri.fraud;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class FraudServices {
    private final FraudRepository fraudRepository;

    public boolean isFraudulentCustomer(Long customerId){
        FraudCheckHistory fraudCheckHistory =
                FraudCheckHistory.builder()
                        .isFrodulent(false)
                        .customerId(customerId)
                        .creatAt(LocalDateTime.now())
                        .build();
        fraudRepository.save(fraudCheckHistory);
        return false;
    }
}
