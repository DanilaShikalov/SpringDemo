package com.taxi.example.controller;

import com.taxi.example.dto.AuthRegisterDTO;
import com.taxi.example.dto.AuthResponseDTO;
import com.taxi.example.service.AuthService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v2/auth")
@Api(tags = "Методы авторизации")
@AllArgsConstructor
public class AuthController {
    private AuthService authService;

    @PostMapping
    public AuthResponseDTO register(@RequestBody AuthRegisterDTO authRegisterDTO) {
        return authService.register(authRegisterDTO);
    }
}
