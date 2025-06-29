package com.example.TimeTracker.Scheduler;

import com.example.TimeTracker.Entities.Employee;
import com.example.TimeTracker.Entities.Users;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    private final JavaMailSender mailSender;   //can use send grid in production

    public NotificationService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendReminder(Users user) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmail());
        message.setSubject("Reminder: Punch OUT Missing");
        message.setText("Hello " + user.getUsername() + ",\n\n"
                + "It looks like you forgot to punch OUT today.\n"
                + "Please remember to do so to keep your work log accurate.\n\n"
                + "Regards,\nTime Tracker Team");

        mailSender.send(message);
        System.out.println("Reminder sent to " + user.getEmail());
    }

}
