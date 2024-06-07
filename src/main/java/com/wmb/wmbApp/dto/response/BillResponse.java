package com.wmb.wmbApp.dto.response;

import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BillResponse {
    private String id;
    private String customerId;
    private String tableId;
    private Date transDate;
    private String transtypeId;
    private List<BillDetailResponse> billDetails;
}
