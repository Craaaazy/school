package com.example.school.task;

import com.example.school.dto.EmailDto;
import com.example.school.event.OnEmailEvent;
import com.example.school.model.User;
import com.example.school.model.UserXBook;
import com.example.school.service.UserXBookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;


@Component
public class CheckLendDateSchduleTask{

    private static final Logger LOGGER = LoggerFactory.getLogger(CheckLendDateSchduleTask.class);

    @Autowired
    private UserXBookService userXBookService;
    @Autowired
    ApplicationEventPublisher eventPublisher;
    @Value("${spring.mail.username}")
    private String usermailname;


    @Scheduled(cron = "* */1 * * * *")
    public void checkDate(){

        LOGGER.info("checking all lend date...");
        List<UserXBook> userXBooks = userXBookService.findAll();

        for (int i = 0; i < userXBooks.size(); i++) {

            Calendar lendedDate = Calendar.getInstance();
            lendedDate.setTime((userXBooks.get(i).getLendedDate()));
            Date nowDate = new Date(System.currentTimeMillis());
            Calendar now = Calendar.getInstance();
            now.setTime(nowDate);

            Long lendDays = now.getTime().getTime() - lendedDate.getTime().getTime();
            if((int) (lendDays/1000/60/60/24) >= 30){
                User user = userXBooks.get(i).getUser();

                LOGGER.info(user.getUsername() + " has overtimed!");

                String subject = "尽快还书!";
                String content = "请尽快还书。 " + user.getUsername() + "/n from xxx";

                EmailDto emailDto = new EmailDto();
                emailDto.setFrom(usermailname);
                emailDto.setTo(user.getEmail());
                emailDto.setSubject(subject);
                emailDto.setMessage(content);

                eventPublisher.publishEvent(new OnEmailEvent(emailDto));

                LOGGER.info("has send an email to " + user.getUsername());
            }

        }

        LOGGER.info("check successed!");

    }

}
