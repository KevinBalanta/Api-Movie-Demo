package com.example.demo.controller;

import com.example.demo.dto.LoginRequestDTO;
import com.example.demo.dto.RegisterRequestDTO;
import com.example.demo.dto.RegisterResponseDTO;
import com.example.demo.service.AuthService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private static final Logger LOG = LogManager.getLogger(AuthController.class);

    @Autowired
    private AuthService service;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDTO> register(
            @RequestBody RegisterRequestDTO request
    ) {
        LOG.info(" REQUEST: "+"METHOD: POST ,URL: /register BODY: "+request.toString());
        LOG.warn(" REQUEST: "+"METHOD: POST ,URL: /register BODY: "+request.toString());
        return ResponseEntity.ok(service.register(request));
    }
    @PostMapping("/")
    public ResponseEntity<RegisterResponseDTO> authenticate(
            @RequestBody LoginRequestDTO request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }
}
