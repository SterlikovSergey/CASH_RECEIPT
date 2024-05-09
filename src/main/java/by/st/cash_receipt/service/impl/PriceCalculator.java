package by.st.cash_receipt.service.impl;

import org.springframework.stereotype.Service;

@Service
public interface PriceCalculator {
    Double calculatePrice();

    Double calculateDiscount();
}
