package by.st.cash_receipt.calculator;

import by.st.cash_receipt.model.DiscountCard;
import by.st.cash_receipt.calculator.impl.PriceCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DiscountPriceCalculatorImpl implements PriceCalculator {
    private PriceCalculator calculator;
    private DiscountCard discountCard;

    public DiscountPriceCalculatorImpl(PriceCalculator calculator, DiscountCard discountCard) {
        this.calculator = calculator;
        this.discountCard = discountCard;
    }


    @Override
    public Double calculatePrice() {
        return calculator.calculatePrice();
    }

    @Override
    public Double calculateDiscount() {
        double totalPrice = calculator.calculatePrice();
        if (discountCard != null) {
            return totalPrice * (discountCard.getDiscountRate() / 100);
        }
        return 0.0;
    }

    @Override
    public Double calculatePromotional() {
        return 0.0;
    }
}
