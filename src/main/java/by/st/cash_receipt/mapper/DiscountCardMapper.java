package by.st.cash_receipt.mapper;

import by.st.cash_receipt.dto.DiscountCardDto;
import by.st.cash_receipt.model.DiscountCard;
import org.springframework.stereotype.Component;

@Component
public class DiscountCardMapper {
    public DiscountCard toDiscountCard(DiscountCardDto dto) {
        return DiscountCard.builder()
                .discountRate(dto.getDiscountRate())
                .build();
    }
}
