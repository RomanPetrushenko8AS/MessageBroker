package com.example.demo;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


public class ScheduledTask1 {
    private static final Logger log = (Logger) LoggerFactory.getLogger(ScheduledTask1.class);

    @Scheduled (fixedRate = 3000)
    public void Task1(){
        Long now = System.currentTimeMillis() / 3000;
        log.info("fixedRate task with three second rate: {}",now);
    }
}
