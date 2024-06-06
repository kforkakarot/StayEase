package com.practice.StayEase.controller;

import com.practice.StayEase.dto.ErrorResponse;
import com.practice.StayEase.dto.JwtAuthenticationResponse;
import com.practice.StayEase.dto.SignInRequest;
import com.practice.StayEase.dto.SignUpRequest;
import com.practice.StayEase.entity.User;
import com.practice.StayEase.exception.UserExistException;
import com.practice.StayEase.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody SignUpRequest signUpRequest){
        try{
            return ResponseEntity.ok(authenticationService.signUp(signUpRequest));
        }catch(UserExistException e){
            log.info(e.getMessage());
            return new ResponseEntity (new ErrorResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SignInRequest signInRequest){
        return ResponseEntity.ok(authenticationService.signin(signInRequest));
    }
}
