package by.st.cash_receipt.service;

import by.st.cash_receipt.model.DiscountCard;
import by.st.cash_receipt.repository.DiscountCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DiscountCardService {
    private final DiscountCardRepository discountCardRepository;

    public DiscountCard addDiscountCard(DiscountCard discountCard) {
        return discountCardRepository.save(discountCard);
    }

    public DiscountCard getDiscountCardBy(Long id) {
        return discountCardRepository.findById(id).orElseThrow();
    }
}
