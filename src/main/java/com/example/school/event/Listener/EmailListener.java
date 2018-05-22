package com.example.school.event.Listener;

import com.example.school.dto.EmailDto;
import com.example.school.event.OnEmailEvent;
import com.example.school.service.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class EmailListener {

    @Autowired
    IEmailService emailService;

    private EmailDto emailDto;

    @EventListener
    @Async
    public void HandleEmailEvent(OnEmailEvent emailEvent){
        this.emailDto = emailEvent.getEmailDto();
        emailService.send(emailDto);
    }

}
