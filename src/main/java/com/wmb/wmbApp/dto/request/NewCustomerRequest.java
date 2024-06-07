package com.wmb.wmbApp.dto.request;

import com.wmb.wmbApp.entity.UserAccount;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewCustomerRequest {

    private String name;
    private String mobilePhone;
    private Boolean isMember;
    private UserAccount userAccount;
}
