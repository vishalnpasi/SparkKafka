package com.exampl.KafkaProducer.KafkaController;

import com.exampl.KafkaProducer.CsvModel.CsvModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.UUID;
import java.util.stream.Stream;

@RestController
public class KafkaController {
    private static String KafkaBrokerEndpoint = "localhost:9092";
    private static String KafkaTopic = "demo";
    private static String CsvFile = "test.csv";
    @GetMapping("/Produce")
    public String Produce() throws URISyntaxException{

        final Producer<String, String> csvProducer = ProducerProperties();

        try{
            URI uri = getClass().getClassLoader().getResource(CsvFile).toURI();
            Stream<String> FileStream = Files.lines(Paths.get(uri));
            FileStream.forEach(line -> {
                System.out.println(line);
                if(line.split(",")[0].equals("step")==false) {

                    try {
                    CsvModel csvModel = StrToObj(line);

                    ObjectMapper objectMapper = new ObjectMapper();

                    String lineAsString = objectMapper.writeValueAsString(csvModel);

                    final ProducerRecord<String, String> csvRecord = new ProducerRecord<String, String>(
                            KafkaTopic, UUID.randomUUID().toString(), lineAsString);

                    csvProducer.send(csvRecord, (metadata, exception) -> {
//                    if(metadata != null){
//                        System.out.println("CsvData: -> "+ csvRecord.key()+" | "+ csvRecord.value());
//                    }
//                    else{
//                        System.out.println("Error Sending Csv Record -> "+ csvRecord.value());
//                    }
                    });
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Producing job completed");
        return "Data Produced Successfully";
    }
    private Producer<String, String> ProducerProperties(){
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaBrokerEndpoint);
        properties.put(ProducerConfig.CLIENT_ID_CONFIG, "KafkaCsvProducer");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        return new KafkaProducer<String, String>(properties);
    }
    public static CsvModel StrToObj(String line){
        String[] fields = line.split(",");
            int step = Integer.parseInt(fields[0]);
            String type = fields[1];
            double amount = Double.parseDouble(fields[2]);
            String nameOrig = fields[3];
            double oldbalanceOrg = Double.parseDouble(fields[4]);
            double newbalanceOrig = Double.parseDouble(fields[5]);
            String nameDest = fields[6];
            double oldbalanceDest = Double.parseDouble(fields[7]);
            double newbalanceDest = Double.parseDouble(fields[8]);
            double isFraud = Double.parseDouble(fields[9]);
            double isFlaggedFraud = Double.parseDouble(fields[10]);
            return new CsvModel(step, type, amount, nameOrig, oldbalanceOrg, newbalanceOrig, nameDest, oldbalanceDest, newbalanceDest, isFraud, isFlaggedFraud);
    }
}
