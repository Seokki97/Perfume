package com.example.perfume.member.service;

import com.example.perfume.member.domain.Blacklist;
import com.example.perfume.member.dto.logoutDto.LogoutRequestDto;
import com.example.perfume.member.exception.MemberAlreadyLogoutException;
import com.example.perfume.member.repository.BlacklistRepository;
import com.example.perfume.member.repository.TokenRepository;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LogoutService {
    private static final int AUTO_DELETE_DELAY = 24*60*60*1000;
    private final TokenRepository tokenRepository;
    private final MemberService memberService;
    private final BlacklistRepository blacklistRepository;

    public LogoutService(TokenRepository tokenRepository, MemberService memberService, BlacklistRepository blacklistRepository) {
        this.tokenRepository = tokenRepository;
        this.memberService = memberService;
        this.blacklistRepository = blacklistRepository;
    }

    @Transactional
    public void permitLogout(LogoutRequestDto logoutRequestDto, String accessToken) {
        Blacklist blackList = Blacklist.builder()
                .member(memberService.findByMemberPk(logoutRequestDto.getMemberId()))
                .accessToken(accessToken)
                .build();
        blacklistRepository.save(blackList);
        tokenRepository.deleteByMemberId(logoutRequestDto.getMemberId());
    }

    @Scheduled(fixedDelay =AUTO_DELETE_DELAY)
    @Transactional
    public void deleteBlackList() {
        blacklistRepository.deleteAll();
    }

    public void isUserAlreadyLogout(String accessToken) {
        if (blacklistRepository.existsByAccessToken(accessToken)) {
            throw new MemberAlreadyLogoutException();
        }
    }
}
