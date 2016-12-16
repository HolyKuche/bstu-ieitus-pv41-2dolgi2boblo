package ru.tdtb.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.tdtb.business.service.TokenUserDetailsService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class AuthController {
    @Autowired
    private TokenUserDetailsService service;

    @RequestMapping(value = "/auth")
    public Map<String, String> getToken() {
        Map<String, String> map = new HashMap<>();
        map.put("tdtb-api-token", service.generateToken());
        return map;
    }
}
