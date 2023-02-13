package com.example.KafkaConsumer.CsvModel;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Document("CsvData")
public class CsvModel {
//    private int step;
    private int step;
    private String type;
    private double amount;
//    private String amount;
    private String nameOrig;
    private double oldbalanceOrg;
//    private String oldbalanceOrg;
    private double newbalanceOrig;
//    private String newbalanceOrig;
    private String nameDest;
    private double oldbalanceDest;
//    private String oldbalanceDest;
    private double newbalanceDest;
//    private String newbalanceDest;
    private double isFraud;
//    private String isFraud;
    private double isFlaggedFraud;
//    private String isFlaggedFraud;
}
