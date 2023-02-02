package org.datn.controller;

import org.datn.entity.Contact;
import org.datn.service.ContactService;
import org.datn.utils.MailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.Date;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/contact")
public class ContactController {
    @Autowired
    ContactService contactService;
    @GetMapping("/get")
    public ResponseEntity getContact(){
        return ResponseEntity.ok(contactService.getByStatus((short) 0));
    }
    @PostMapping("/send")
    public void sendContact(
            @RequestBody Contact contact
    ){
        contact.setStatus((short)0);
        contact.setCreated(new Date());
        contactService.save(contact);
    }
    @PostMapping("/sendMail")
    public void sendMail(
            @ModelAttribute("email") String email,
            @ModelAttribute("mail") String mail,
            @ModelAttribute("title") String title
            ) throws MessagingException {
        MailSender.sendCode(email,mail,title);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteContact(@PathVariable("id")long id ){
        contactService.delete(id);
    }

    @PostMapping("/afterSendMail")
    public ResponseEntity afterSendMail(
            @RequestBody() Contact contact
    ){
        contact.setStatus((short)1);
        return ResponseEntity.ok(contactService.save(contact));
    }
    @GetMapping("/contacted")
    public ResponseEntity contacted(){
        return ResponseEntity.ok(contactService.getByStatus((short)1));
    }
}
