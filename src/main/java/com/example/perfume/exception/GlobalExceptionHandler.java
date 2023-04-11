package com.example.perfume.exception;

import com.example.perfume.member.domain.Member;
import com.example.perfume.member.exception.MemberAlreadyLogoutException;
import com.example.perfume.member.exception.TokenExpiredException;
import com.example.perfume.recommend.exception.RecommendNotFoundException;
import com.example.perfume.member.exception.TokenInvalidException;
import com.example.perfume.member.exception.UserNotFoundException;
import com.example.perfume.oauth.exception.EmailNotFoundException;
import com.example.perfume.oauth.exception.MemberAlreadyExistException;
import com.example.perfume.perfume.exception.BrandNotFoundException;
import com.example.perfume.perfume.exception.PerfumeNotFoundException;
import com.example.perfume.post.exception.PostNotFoundException;
import com.example.perfume.survey.exception.MoodNotFoundException;
import com.example.perfume.survey.exception.ScentNotFoundException;
import com.example.perfume.survey.exception.SeasonNotFoundException;
import com.example.perfume.survey.exception.SurveyNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final int NOT_FOUND_ERROR = 404;
    private static final int UNAUTHORIZED_ERROR = 401;

    @ExceptionHandler(ScentNotFoundException.class)
    public ResponseEntity<?> handleScentNotFoundException(ScentNotFoundException e) {
        return ResponseEntity.status(NOT_FOUND_ERROR).body(e.getMessage());
    }

    @ExceptionHandler(SeasonNotFoundException.class)
    public ResponseEntity<?> handleSeasonNotFoundException(SeasonNotFoundException e) {
        return ResponseEntity.status(NOT_FOUND_ERROR).body(e.getMessage());
    }

    @ExceptionHandler(MoodNotFoundException.class)
    public ResponseEntity<?> handleMoodNotFoundException(MoodNotFoundException e) {
        return ResponseEntity.status(NOT_FOUND_ERROR).body(e.getMessage());
    }

    @ExceptionHandler(SurveyNotFoundException.class)
    public ResponseEntity<?> handleSurveyNotFoundException(SurveyNotFoundException e) {
        return ResponseEntity.status(NOT_FOUND_ERROR).body(e.getMessage());
    }

    @ExceptionHandler(PostNotFoundException.class)
    public ResponseEntity<?> handlePostNotFoundException(PostNotFoundException e) {
        return ResponseEntity.status(NOT_FOUND_ERROR).body(e.getMessage());
    }

    @ExceptionHandler(PerfumeNotFoundException.class)
    public ResponseEntity<?> handlePerfumeNotFoundException(PerfumeNotFoundException e) {
        return ResponseEntity.status(NOT_FOUND_ERROR).body(e.getMessage());
    }

    @ExceptionHandler(BrandNotFoundException.class)
    public ResponseEntity<?> handleBrandNotFoundException(BrandNotFoundException e) {
        return ResponseEntity.status(NOT_FOUND_ERROR).body(e.getMessage());
    }

    @ExceptionHandler(MemberAlreadyExistException.class)
    public ResponseEntity<?> handleMemberAlreadyExistException(MemberAlreadyExistException e) {
        return ResponseEntity.status(NOT_FOUND_ERROR).body(e.getMessage());
    }

    @ExceptionHandler(EmailNotFoundException.class)
    public ResponseEntity<?> handleEmailNotFoundException(EmailNotFoundException e) {
        return ResponseEntity.status(NOT_FOUND_ERROR).body(e.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<?> handleTokenInvalidException(TokenInvalidException e) {
        return ResponseEntity.status(UNAUTHORIZED_ERROR).body(e.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<?> handleUserNotFoundException(UserNotFoundException e) {
        return ResponseEntity.status(NOT_FOUND_ERROR).body(e.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<?> handleRecommendNotFoundException(RecommendNotFoundException e) {
        return ResponseEntity.status(NOT_FOUND_ERROR).body(e.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<?> handleTokenExpiredException(TokenExpiredException e) {
        return ResponseEntity.status(UNAUTHORIZED_ERROR).body(e.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<?> handleMemberAlreadyLogoutException(MemberAlreadyLogoutException e) {
        return ResponseEntity.status(UNAUTHORIZED_ERROR).body(e.getMessage());
    }
}
