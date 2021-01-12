package com.example.testc.config;

import com.example.testc.dto.PersonaDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PersonaListener {

    @RabbitListener(queues = "${rabbitmq.queue}")
    public PersonaDto personaFromQueue(PersonaDto personaDto){
        System.out.println("Persona recivida en c");
        System.out.println(personaDto.toString());
        personaDto.setNombre("Alfonso");
        return personaDto;
    }

    /*@RabbitListener(queues = "cola1")
    public void mensajeFromQueue1(PersonaDto personaDto){
        System.out.println("Mensaje desde cola 1");
        System.out.println(personaDto.toString());
    }

    @RabbitListener(queues = "cola2")
    public void mensajeFromQueue2(PersonaDto personaDto){
        System.out.println("Mensaje desde cola 2");
        System.out.println(personaDto.toString());
    }*/

}
