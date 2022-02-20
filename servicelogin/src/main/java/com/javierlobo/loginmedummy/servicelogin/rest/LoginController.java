package com.javierlobo.loginmedummy.servicelogin.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LoginController {
	
	@Value("${configuracion.texto}")
	//@Value("Hola Mundo")
	private String message; // =  "Hello World!!!";
	
	@Value("${eureka.instance.instance-id}") 
	private String instanceId;

    @GetMapping("/whois")
    public @ResponseBody String hello() {
    	System.out.println(String.valueOf(instanceId).concat(" :--: ").concat(message));
        return message;
    }

}