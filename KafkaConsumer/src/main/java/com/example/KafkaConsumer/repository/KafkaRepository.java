package com.example.KafkaConsumer.repository;

import com.example.KafkaConsumer.CsvModel.CsvModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KafkaRepository extends MongoRepository<CsvModel,String> {
}
