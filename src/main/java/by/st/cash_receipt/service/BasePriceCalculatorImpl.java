package by.st.cash_receipt.service;

import by.st.cash_receipt.model.Product;
import by.st.cash_receipt.service.impl.PriceCalculator;;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class BasePriceCalculatorImpl implements PriceCalculator {
    private Map<Product, Integer> products;

    public BasePriceCalculatorImpl(Map<Product, Integer> products) {
        this.products = products;
    }

    @Override
    public Double calculatePrice() {
        double totalPrice = 0.0;
        for (Product product : products.keySet()) {
            totalPrice += product.getPrice() * products.get(product);
        }
        return totalPrice;
    }

    @Override
    public Double calculateDiscount() {
        return 0.0;
    }
}
