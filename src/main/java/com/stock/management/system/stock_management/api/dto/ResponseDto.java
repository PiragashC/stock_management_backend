package com.stock.management.system.stock_management.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class ResponseDto {
    private String responseMessage;

    public ResponseDto(String responseMessage) {
        this.responseMessage = responseMessage;
    }
}
