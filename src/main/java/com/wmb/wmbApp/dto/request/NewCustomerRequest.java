package com.wmb.wmbApp.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewCustomerRequest {

    @NotBlank(message = "name is required")
    private String name;

    private String mobilePhone;

    @NotBlank(message = "isMember is required")
    private Boolean isMember;
}
