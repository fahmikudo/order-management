package com.platform.order.service.domain.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UpdateOrderRequest {

    @JsonIgnore
    private Integer orderId;
    private String orderStatus;

}
