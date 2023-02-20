package com.marri.fraud;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Fraud")
@Table(name = "fraud")
public class FraudCheckHistory {
    @Id
    @SequenceGenerator(
            sequenceName = "sequence_fraud",
            name = "sequence_fraud",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "sequence_fraud"
    )
    private Long id;
    private Long customerId;
    private boolean isFrodulent;
    private LocalDateTime creatAt;
}
