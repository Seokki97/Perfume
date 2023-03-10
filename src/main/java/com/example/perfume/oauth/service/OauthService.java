package com.example.perfume.oauth.service;

import com.example.perfume.member.domain.Member;
import com.example.perfume.member.dto.memberDto.MemberRequestDto;
import com.example.perfume.member.repository.MemberRepository;
import com.example.perfume.member.service.MemberService;
import com.example.perfume.member.service.jwt.JwtProvider;
import com.example.perfume.oauth.OauthType;
import com.example.perfume.oauth.exception.EmailNotFoundException;
import com.example.perfume.oauth.exception.MemberAlreadyExistException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;

@Service
public class OauthService {

    private final MemberService memberService;

    private final JwtProvider jwtProvider;
    private final MemberRepository memberRepository;

    private OauthService(MemberService memberService, JwtProvider jwtProvider,
                        MemberRepository memberRepository) {
        this.memberService = memberService;
        this.jwtProvider = jwtProvider;

        this.memberRepository = memberRepository;
    }

    private HttpHeaders setHttpHeaders() {

        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.add(OauthType.CONTENT_TYPE.getName(), OauthType.CONTENT_TYPE.getType());
        return httpHeaders;
    }

    private LinkedMultiValueMap<String, String> setHttpBody(String code) {

        LinkedMultiValueMap<String, String> accessTokenParams = new LinkedMultiValueMap<>();
        accessTokenParams.add(OauthType.GRANT_TYPE.getName(), OauthType.GRANT_TYPE.getType());
        accessTokenParams.add(OauthType.CLIENT_ID.getName(), OauthType.CLIENT_ID.getType());
        accessTokenParams.add(OauthType.REDIRECT_URI.getName(), OauthType.REDIRECT_URI.getType());
        accessTokenParams.add("code", code);
        return accessTokenParams;
    }

    private RestTemplate createRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate;
    }

    //???????????? ?????? ??? ??????
    public ResponseEntity<String> getResponseFromServer(String url, String code, HttpHeaders httpHeaders) {
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>((setHttpBody(code)), httpHeaders);


        ResponseEntity<String> response = createRestTemplate().exchange(url, HttpMethod.POST, request, String.class);

        return response;
    }

    public Member loadUserProfile(String code, HttpSession httpSession) {
        try {
            JSONParser jsonParser = new JSONParser();
            HttpHeaders httpHeaders = setHttpHeaders();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(getResponseFromServer("https://kauth.kakao.com/oauth/token", code, httpHeaders).getBody());
            httpSession.setAttribute("Authorization", jsonObject.get("access_token"));

            httpHeaders.add("Authorization", "Bearer " + jsonObject.get("access_token"));

            ResponseEntity<String> responseEntity = getResponseFromServer("https://kapi.kakao.com/v2/user/me", code, httpHeaders);

            JSONObject profile = (JSONObject) jsonParser.parse(responseEntity.getBody());
            JSONObject properties = (JSONObject) profile.get("properties");
            JSONObject kakaoAccount = (JSONObject) profile.get("kakao_account");

            MemberRequestDto memberRequestDto = MemberRequestDto.builder()
                    .memberId((Long) profile.get("id"))
                    .nickname((String) properties.get("nickname"))
                    .email((String) kakaoAccount.get("email"))
                    .thumbnailImage((String) properties.get("thumbnail_image"))
                    .build();
            saveUserProfile(memberRequestDto);
            return memberService.findByMemberPk(memberRequestDto.getMemberId());

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUserProfile(MemberRequestDto memberRequestDto) {
        Member member = Member.builder()
                .memberId(memberRequestDto.getMemberId())
                .email(memberRequestDto.getEmail())
                .nickname(memberRequestDto.getNickname())
                .thumbnailImage(memberRequestDto.getThumbnailImage())
                .build();

        isAgreeEmailUsing(memberRequestDto.getEmail());

        if (memberService.isAlreadyExistMember(memberRequestDto)) {
            throw new MemberAlreadyExistException();
        }
        memberService.saveMemberProfile(member);
    }

    public boolean isAgreeEmailUsing(String email) {
        if (email == null) {
            throw new EmailNotFoundException();
        }
        return true;
    }


}