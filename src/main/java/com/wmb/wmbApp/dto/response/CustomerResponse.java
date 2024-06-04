package com.wmb.wmbApp.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerResponse {
    private String id;
    private String name;
    private String mobilePhone;
    private Boolean isMember;
    private String userAccountId;
}
