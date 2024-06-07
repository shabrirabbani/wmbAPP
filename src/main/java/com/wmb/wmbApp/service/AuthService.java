package com.wmb.wmbApp.service;

import com.wmb.wmbApp.dto.request.AuthRequest;
import com.wmb.wmbApp.dto.response.LoginResponse;
import com.wmb.wmbApp.dto.response.RegisterResponse;

public interface AuthService {
    RegisterResponse register(AuthRequest request);
    RegisterResponse registerAdmin(AuthRequest request);
    LoginResponse login(AuthRequest request);
}
