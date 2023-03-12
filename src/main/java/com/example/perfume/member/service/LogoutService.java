package com.example.perfume.member.service;

import com.example.perfume.member.dto.loginDto.LogoutRequestDto;
import com.example.perfume.member.repository.TokenRepository;
import org.springframework.stereotype.Service;

@Service
public class LogoutService {
    private final TokenRepository tokenRepository;


    private LogoutService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    public void permitLogout(LogoutRequestDto logoutRequestDto){
        tokenRepository.deleteByRefreshToken(logoutRequestDto.getRefreshToken());
    }
}
