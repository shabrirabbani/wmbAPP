package com.wmb.wmbApp.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SearchMenuRequest {
    private String menuName;
    private Long price;
}
