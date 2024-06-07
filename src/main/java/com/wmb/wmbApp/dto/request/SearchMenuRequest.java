package com.wmb.wmbApp.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SearchMenuRequest {
    private String name;
    private Long price;

    private Integer page;
    private Integer size;

    private String sortBy;
    private String direction;
}
