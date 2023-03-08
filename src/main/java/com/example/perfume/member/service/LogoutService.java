package com.example.perfume.member.service;

import com.example.perfume.member.domain.Member;
import com.example.perfume.member.domain.Token;
import com.example.perfume.member.dto.memberDto.LogoutRequestDto;
import com.example.perfume.member.exception.UserNotFoundException;
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
