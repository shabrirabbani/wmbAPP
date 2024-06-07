package com.wmb.wmbApp.service;

import com.wmb.wmbApp.dto.response.JwtClaims;
import com.wmb.wmbApp.entity.UserAccount;

public interface JwtService {
    String generateToken(UserAccount userAccount);
    boolean verifyJwtToken(String token);
    JwtClaims getClaimsByToken(String token);
}
