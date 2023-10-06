package com.sansa.jumpdigitalhackathon.response;

import com.sansa.jumpdigitalhackathon.dto.SkinDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PurchaseResponse {
    private String msg;
    private SkinDTO skin;
}
