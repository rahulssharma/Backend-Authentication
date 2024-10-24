package com.rahul.registrationapp.service;

import com.rahul.registrationapp.appuser.RegistrationRequest;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {
    public String register(RegistrationRequest request) {
        return "works";
    }
}
