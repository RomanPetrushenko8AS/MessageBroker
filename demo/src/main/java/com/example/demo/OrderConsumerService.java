package com.example.demo;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumerService {
    private final SimpleMessageBroker broker;
    private static final Logger log = LoggerFactory.getLogger(OrderConsumerService.class);
    private Thread consumerThread;

    public OrderConsumerService(SimpleMessageBroker broker) {
        this.broker = broker;
    }

    @PostConstruct
    public void startListening(){
        consumerThread = new Thread(() ->{
           log.info("[CONSUMER] Фоновый сервис запущен и слушает 'orders'..." );

           while(!Thread.currentThread().isInterrupted()){
               try{
                   String message = broker.consume("orders");
                   log.info("[CONSUMER] Принято из брокера и обработка: {} ", message);;
               }catch (InterruptedException e){
                   log.warn("[CONSUMER] Поток коньсьюмера получил сигнал остановки");
                   Thread.currentThread().interrupt();
               }catch(Exception e){
                   log.error("[CONSUMER] Непредвиденная ошибка при обработке сообщения: {}",e.getMessage(),e);
               }
           }
        },"Order-Consumer-Thread");

        consumerThread.start();
    }

    @PreDestroy
    public void stopListening(){
        if(consumerThread != null){
            log.info("[CONSUMER] Остановка фонового потока...");
            consumerThread.interrupt();
        }
    }
}

