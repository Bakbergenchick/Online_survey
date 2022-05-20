package com.atymtay.online_survey.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaMessage {

    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public KafkaMessage(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public KafkaMessage() {
    }

    @Scheduled(fixedDelay = 4000L)
    public void requestKafkaMsg(){
        kafkaTemplate.send("Kafka", "Hello World");

        log.info("Successfully sended!");
    }


}
