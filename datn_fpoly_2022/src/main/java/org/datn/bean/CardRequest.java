package org.datn.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data@NoArgsConstructor@AllArgsConstructor
@Component
public class CardRequest {
    private Long userId;
    private Long cardPriceId;
    private String seri;
    private String code;
}
