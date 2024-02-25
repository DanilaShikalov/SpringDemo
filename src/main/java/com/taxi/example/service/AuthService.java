package com.taxi.example.service;

import com.taxi.example.dto.AuthRegisterDTO;
import com.taxi.example.dto.AuthResponseDTO;
import com.taxi.example.entity.User;
import com.taxi.example.entity.UserRole;
import com.taxi.example.repository.UserRepository;
import com.taxi.example.security.JwtUtils;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class AuthService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private JwtUtils jwtUtils;

    @Transactional
    public AuthResponseDTO register(AuthRegisterDTO authRegisterDTO) {
        User user = new User(authRegisterDTO.getLogin(), passwordEncoder.encode(authRegisterDTO.getPassword()), UserRole.ADMIN);
        if (userRepository.findByLogin(authRegisterDTO.getLogin()) == null) {
            userRepository.save(user);
        }
        return new AuthResponseDTO(jwtUtils.generateToken(user));
    }
}
