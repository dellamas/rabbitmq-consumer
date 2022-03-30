package com.mservice.inventoryprice.controller;


import com.mservice.inventoryprice.services.RabbitMQService;
import consts.RabbitMQConsts;
import dto.PriceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="price")


public class PriceController {
    @Autowired
    private RabbitMQService rabbitMQService;
    @PutMapping
    private ResponseEntity changePrice(@RequestBody PriceDto priceDto){
        this.rabbitMQService.sendMessage(RabbitMQConsts.ROW_PRICE, priceDto);
        return new ResponseEntity(HttpStatus.OK);
    }
}
