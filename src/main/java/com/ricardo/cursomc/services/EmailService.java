package com.ricardo.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.ricardo.cursomc.domain.Pedido;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
	
	

}
