package com.example.demo;

import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;


@Service
public class OrderProducerService {
    private final SimpleMessageBroker broker;
    private final static Logger log =  LoggerFactory.getLogger(OrderProducerService.class);
    private int counter = 1;

    public OrderProducerService(SimpleMessageBroker broker) {  // ← внедрение
        this.broker = broker;
    }

    @Scheduled(fixedRate = 3000)
    public void generateOrder(){
        String order = "Заказ № " + counter++;
        log.info("[SCHEDULED PRODUCER] Создан новый {}", order);
        broker.produce("orders",order);
    }
}
