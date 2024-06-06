package com.practice.StayEase.service;

import com.practice.StayEase.dto.JwtAuthenticationResponse;
import com.practice.StayEase.dto.SignInRequest;
import com.practice.StayEase.dto.SignUpRequest;
import com.practice.StayEase.entity.User;
import com.practice.StayEase.exception.UserExistException;

public interface AuthenticationService {
    User signUp(SignUpRequest signUpRequest) throws UserExistException;
    JwtAuthenticationResponse signin(SignInRequest signInRequest);
}
