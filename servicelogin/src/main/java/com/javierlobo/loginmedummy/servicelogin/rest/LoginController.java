package com.javierlobo.loginmedummy.servicelogin.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LoginController {
	
	@Value("${eureka.instance.instance-id}") 
	private String instanceId;

    @GetMapping("/whoim")
    public @ResponseBody String hello() {
        return instanceId;
    }

}