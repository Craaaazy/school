package com.example.school.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

@Component
public class ScheduledTasks {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

//    @Scheduled(cron = "*/10 * * * * ?") //must return void and have no arguments
//    public void reportCurrentTime() {
//        log.info("The time is now {}", dateFormat.format(new Date(System.currentTimeMillis())));
//    }

}
