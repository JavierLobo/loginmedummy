package com.javierlobo.loginmedummy.servicelogin.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LoginController {
	
    @GetMapping("/whois")
    public @ResponseBody String hello() {
        return "Hello World!!!";
    }

}
