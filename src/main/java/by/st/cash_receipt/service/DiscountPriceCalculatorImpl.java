package by.st.cash_receipt.service;

import by.st.cash_receipt.model.DiscountCard;
import by.st.cash_receipt.service.impl.PriceCalculator;
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
        double totalPrice = calculator.calculatePrice();
        double discount = totalPrice * (discountCard.getDiscountRate() / 100);
        return totalPrice - discount;
    }

    @Override
    public Double calculateDiscount() {
        double totalPrice = calculator.calculatePrice();
        return totalPrice * (discountCard.getDiscountRate() / 100);
    }
}
