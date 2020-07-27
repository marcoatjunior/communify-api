package com.communify.api.service;

import static com.communify.api.builder.GenericBuilder.of;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import lombok.Getter;

@Service
@Getter
public class TermService implements ITermService {

    private static final String DEFAULT_SUBJECT_SENDER  = "communify@unilasalle.edu.br";

    private static final String DEFAULT_SUBJECT_MESSAGE = "Communify informa: Termo de responsabilidade";

    @Autowired
    private JavaMailSender emailSender;

    @Override
    public void send(String email, String ip) {
        getEmailSender().send(of(SimpleMailMessage::new).with(SimpleMailMessage::setFrom, DEFAULT_SUBJECT_SENDER)
                .with(SimpleMailMessage::setSubject, DEFAULT_SUBJECT_MESSAGE).with(SimpleMailMessage::setTo, email)
                .with(SimpleMailMessage::setText,
                    "Você realizou seu primeiro acesso no aplicativo La Salle Communify utilizando o e-mail "
                        + email + "."
                        + "Caso você não tenha feito o acesso, entre em contato imediatamente com a central através do endereço: "
                        + DEFAULT_SUBJECT_SENDER
                        + ". IP que realizou o acesso: " + ip)
                .build());
    }
}
