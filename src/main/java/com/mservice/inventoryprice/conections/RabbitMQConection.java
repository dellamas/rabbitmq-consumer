package com.mservice.inventoryprice.conections;


import consts.RabbitMQConsts;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.stereotype.Component;
import org.springframework.amqp.core.Queue;

import javax.annotation.PostConstruct;

@Component


// criando a fila
public class RabbitMQConection {
    private static final String EXCHANGE_NAME = "amq.direct";
// criando um construtor
    private  AmqpAdmin amqpAdmin;
    public RabbitMQConection(AmqpAdmin amqpAdmin){
        this.amqpAdmin = amqpAdmin;

    }

    private Queue fila(String nomeFila){
        return new Queue(nomeFila,true,false,false);
    }
// criando a EXCHANGE
    private DirectExchange directExchange(){
        return new DirectExchange(EXCHANGE_NAME);
    }
// criando o relacionamento entre a fila e a exchange , o Binding
    private Binding relationship(Queue fila, DirectExchange exchange){
        return new Binding(fila.getName(), Binding.DestinationType.QUEUE, exchange.getName(),fila.getName(),null);

    }
// méthodo que usara as funções ja criadas e de chamar as filas
    @PostConstruct // assim que contruir a classe abaixo ele vai criar as filas
    private void adiction(){
        Queue row_inventory = this.fila(RabbitMQConsts.ROW_INVENTORY);
        Queue row_price = this.fila(RabbitMQConsts.ROW_PRICE);

        DirectExchange exchange = this.directExchange();

        Binding linkInventory = this.relationship(row_inventory,exchange);
        Binding linkPrice = this.relationship(row_price,exchange);

        //criando as filas no rabbitmq

        this.amqpAdmin.declareQueue(row_inventory);
        this.amqpAdmin.declareQueue(row_price);

        //criando as exchange
        this.amqpAdmin.declareExchange(exchange);

        this.amqpAdmin.declareBinding(linkInventory);
        this.amqpAdmin.declareBinding(linkPrice);
    }

}
