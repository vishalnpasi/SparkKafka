package com.example.KafkaConsumer.consumer;


import com.example.KafkaConsumer.CsvModel.CsvModel;
import com.example.KafkaConsumer.repository.KafkaRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class KafkaConsumer {
    @Autowired
//    private CsvModel model;
    private KafkaRepository repository;

    @KafkaListener(topics = "demo", groupId = "group_id")
    public void consume(String message) throws JsonProcessingException {

        System.out.println("message = " + message);
        ObjectMapper objectMapper = new ObjectMapper();
        CsvModel csvModel = objectMapper.readValue(message,CsvModel.class);
        repository.save(csvModel);

//        try {
//
//                System.out.println("message = " + message);
//                String[] fields = message.split(",");
//        if(fields[0].equals("step")==false)
//        {
//                    int step = Integer.parseInt(fields[0]);
//            String type = fields[1];
//            double amount = Double.parseDouble(fields[2]);
//            String nameOrig = fields[3];
//            double oldbalanceOrg = Double.parseDouble(fields[4]);
//            double newbalanceOrig = Double.parseDouble(fields[5]);
//            String nameDest = fields[6];
//            double oldbalanceDest = Double.parseDouble(fields[7]);
//            double newbalanceDest = Double.parseDouble(fields[8]);
//            double isFraud = Double.parseDouble(fields[9]);
//            double isFlaggedFraud = Double.parseDouble(fields[10]);
//            CsvModel csvObj = new CsvModel(step,type,amount,nameOrig,oldbalanceOrg,newbalanceOrig,nameDest,oldbalanceDest,newbalanceDest,isFraud,isFlaggedFraud);
//            repository.save(csvObj);
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }
    }
}
