package testMySQL.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/secret")
public class SecretApi {

    @GetMapping
    public String getSecret() {
        return "This is secret api";
    }
}