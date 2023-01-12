package org.datn.controller;

import org.datn.utils.MailSender;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

@RestController
@RequestMapping("/api/mail")
public class MailController {
    @PostMapping("/sendMail")
    public void sendMail(
            @ModelAttribute("mail") String mail,
            @ModelAttribute("email") String emailAddress
    ) throws MessagingException {
        MailSender.sendCode(emailAddress,mail,"Về việc duyệt khóa học");
    }
}
