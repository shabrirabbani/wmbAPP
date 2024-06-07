package com.wmb.wmbApp.controller;

import com.wmb.wmbApp.constant.APIUrl;
import com.wmb.wmbApp.dto.request.AuthRequest;
import com.wmb.wmbApp.dto.response.CommonResponse;
import com.wmb.wmbApp.dto.response.LoginResponse;
import com.wmb.wmbApp.dto.response.RegisterResponse;
import com.wmb.wmbApp.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = APIUrl.AUTH_API)
public class AuthController {

    private final AuthService authService;

    @PostMapping(path = "/register")
    public ResponseEntity<CommonResponse<?>> registerUser(@RequestBody AuthRequest request) {
        RegisterResponse register = authService.register(request);
        CommonResponse<RegisterResponse> response = CommonResponse.<RegisterResponse>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("successfully save data")
                .data(register)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping(path = "/login")
    public ResponseEntity<CommonResponse<?>> login(@RequestBody AuthRequest request) {
        LoginResponse login = authService.login(request);
        CommonResponse<LoginResponse> response = CommonResponse.<LoginResponse>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("successfully login")
                .data(login)
                .build();

        return ResponseEntity.ok(response);
    }
}
