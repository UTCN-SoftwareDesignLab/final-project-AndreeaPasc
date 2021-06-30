package com.finalprojectandreeapasc.email.controller;

import com.finalprojectandreeapasc.email.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

import static com.finalprojectandreeapasc.UrlMapping.CUSTOMERS;

@RestController
@RequestMapping(CUSTOMERS)
@RequiredArgsConstructor
public class EmailController {

    private final EmailService emailService;

    @PostMapping
    public void sendEmail(@RequestBody String email) throws UnsupportedEncodingException {
        emailService.sendMail(email);
    }
}
