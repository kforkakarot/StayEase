package com.practice.StayEase.service;

import com.practice.StayEase.dto.JwtAuthenticationResponse;
import com.practice.StayEase.dto.SignInRequest;
import com.practice.StayEase.dto.SignUpRequest;
import com.practice.StayEase.entity.User;

public interface AuthenticationService {
    User signUp(SignUpRequest signUpRequest);
    JwtAuthenticationResponse signin(SignInRequest signInRequest);
}
