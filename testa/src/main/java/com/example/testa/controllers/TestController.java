package com.example.testa.controllers;

import com.example.testa.config.MessageConfig;
import com.example.testa.config.Receiver;
import com.example.testa.dto.PersonaDto;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionListener;
import org.springframework.amqp.rabbit.core.ChannelCallback;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@RestController
@RequestMapping("apia")
public class TestController {

    @Autowired
    private AmqpTemplate amqpTemplate;
    //@Autowired
    //private Receiver receiver;

    @GetMapping("info")
    public String info(){
        return "Info a Ok";
    }

    @GetMapping("sendMessage")
    private String sendMessage() throws InterruptedException {
        PersonaDto persona = new PersonaDto();
        persona.setNombre("Edgar");
        persona.setCorreo("edgar@gmail.com");
        persona.setFechaNacimiento(new Date());
        System.out.println("Enviando mensaje desde A: " + persona.toString());
        //amqpTemplate.convertAndSend(MessageConfig.topicExchange, MessageConfig.routingKey, persona);
        amqpTemplate.convertAndSend("fanoutExc", MessageConfig.routingKey, persona);
        return "ok";
    }

    @GetMapping("sendRecMessage")
    private String sendRecMessage() throws InterruptedException {
        PersonaDto persona = new PersonaDto();
        persona.setNombre("Edgar");
        persona.setCorreo("edgar@gmail.com");
        persona.setFechaNacimiento(new Date());
        System.out.println("Enviando y Recibiendo mensaje desde A: " + persona.toString());
        //amqpTemplate.convertAndSend(MessageConfig.topicExchange, MessageConfig.routingKey, persona);
        //PersonaDto personaSalida = (PersonaDto) amqpTemplate.convertSendAndReceive(MessageConfig.topicExchange, MessageConfig.routingKey, persona);
        PersonaDto personaDto = amqpTemplate.convertSendAndReceiveAsType(
                MessageConfig.topicExchange, MessageConfig.routingKey,
                persona, new ParameterizedTypeReference<PersonaDto>(){});
        System.out.println("Persona recibida en A: " + personaDto.toString());
        return "ok";
    }

}
