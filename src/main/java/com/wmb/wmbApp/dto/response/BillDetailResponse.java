package com.wmb.wmbApp.dto.response;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Builder
public class BillDetailResponse {
    private String id;
    private String menuId;
    private Integer qty;
}
