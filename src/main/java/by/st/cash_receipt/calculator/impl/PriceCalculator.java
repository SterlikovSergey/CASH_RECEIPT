package by.st.cash_receipt.calculator.impl;

import org.springframework.stereotype.Service;

@Service
public interface PriceCalculator {
    Double calculatePrice();

    Double calculateDiscount();

    Double calculatePromotional();
}
