package com.example.demo;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

@Component
public class SimpleMessageBroker {
    // Хранилище очередей по именам (топикам)
    private final Map<String, BlockingQueue<String>> queues = new ConcurrentHashMap<>();

    // Отправка сообщения в указанную очередь (Producer)
    public void produce(String queueName, String message) {
        // Создаем очередь, если её еще нет, и кладем сообщение
        queues.computeIfAbsent(queueName, k -> new LinkedBlockingQueue<>())
                .offer(message);
    }

    // Получение сообщения из очереди (Consumer)
    // Если очередь пуста, метод блокирует поток (ждет появления данных)
    public String consume(String queueName) throws InterruptedException {
        BlockingQueue<String> queue = queues.computeIfAbsent(queueName, k -> new LinkedBlockingQueue<>());
        return queue.take(); // Блокирует вызов
    }
}