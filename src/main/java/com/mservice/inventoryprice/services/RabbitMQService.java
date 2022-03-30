package com.mservice.inventoryprice.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
//criação de um método que  envia a menssagem para o rabbitmq

public class RabbitMQService {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private ObjectMapper objectMapper;

    public void sendMessage(String rowName, Object message) {
        try {
            String messageJson= this.objectMapper.writeValueAsString(message);
            this.rabbitTemplate.convertAndSend(rowName, messageJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
