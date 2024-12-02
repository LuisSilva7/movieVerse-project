package org.movieverse.movieverse_backend.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@AllArgsConstructor
@Data
public class CartTotalAmountResponse {
    private BigDecimal totalAmount;
}
