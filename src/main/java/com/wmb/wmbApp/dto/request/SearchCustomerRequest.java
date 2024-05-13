package com.wmb.wmbApp.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SearchCustomerRequest {
    private String name;
    private String phone;
    private Boolean isMember;
}
