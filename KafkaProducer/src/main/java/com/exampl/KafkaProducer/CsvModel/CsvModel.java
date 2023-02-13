package com.exampl.KafkaProducer.CsvModel;

import lombok.*;
//import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
//@Document("CsvData")
public class CsvModel {
    private int step;
    private String type;
    private double amount;
    private String nameOrig;
    private double oldbalanceOrg;
    private double newbalanceOrig;
    private String nameDest;
    private double oldbalanceDest;
    private double newbalanceDest;
    private double isFraud;
    private double isFlaggedFraud;
}
