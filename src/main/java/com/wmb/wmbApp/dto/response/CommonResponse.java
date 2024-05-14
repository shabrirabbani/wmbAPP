package com.wmb.wmbApp.dto.response;

public class CommonResponse<T> {
    private Integer statusCode;
    private String message;
    private T data; // nanti datanya bisa dinamis, array atau object
    private PagingResponse paging;
}
