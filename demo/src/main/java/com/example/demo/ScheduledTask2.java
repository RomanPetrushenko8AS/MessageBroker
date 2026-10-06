package com.example.demo;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


public class ScheduledTask2 {
    private static final Logger log = (Logger) LoggerFactory.getLogger(ScheduledTask2.class);
    @Scheduled(cron = "37-39 * * * * *")
    public void Task2(){
        Long now = System.currentTimeMillis() /5000;
        log.info("fixedDelay task with five second delay: {}",now);

    }

}
