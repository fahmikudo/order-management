package com.platform.order.service.domain.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DefaultResponse<T> {
    private String message;
    private Integer statusCode;
    private boolean isSuccess;
    private T orderData;
}
