package com.example.school.event.Listener;


import com.example.school.event.TestEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;


@Component
public class TestApplicationStartedListener {

    private Logger logger = LoggerFactory.getLogger(TestApplicationStartedListener.class);

    @EventListener
    public void test(TestEvent testEvent) {
        logger.info("==testListenerStartedEventListener==");
    }
}
