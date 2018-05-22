package com.example.school.event;

import com.example.school.dto.EmailDto;
import org.springframework.context.ApplicationEvent;

public class OnEmailEvent extends ApplicationEvent {

    private EmailDto emailDto;

    public OnEmailEvent(EmailDto emailDto) {
        super(emailDto);
        this.emailDto = emailDto;
    }

    public EmailDto getEmailDto(){
        return this.emailDto;
    }

}
