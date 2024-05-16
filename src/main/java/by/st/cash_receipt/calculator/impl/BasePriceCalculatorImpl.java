package by.st.cash_receipt.calculator.impl;

import by.st.cash_receipt.model.Product;
import by.st.cash_receipt.calculator.PriceCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class BasePriceCalculatorImpl implements PriceCalculator {
    private Map<Product, Integer> products;
    private final Map<Product, Integer> productsIsPromotional = new HashMap<>();

    public BasePriceCalculatorImpl(Map<Product, Integer> products) {
        this.products = new HashMap<>(products);
        products.forEach((product, quantity) -> {
            if (product.getIsPromotional() && quantity > 5) {
                this.products.remove(product);
                this.productsIsPromotional.put(product, quantity);
            }
        });
    }

    @Override
    public Double calculatePrice() {
        double totalPrice = 0.0;
        for (Product product : products.keySet()) {
            totalPrice += product.getPrice() * products.get(product);
        }
        for(Product product : productsIsPromotional.keySet()) {
            totalPrice += product.getPrice() * productsIsPromotional.get(product);
        }
        return totalPrice;
    }

    @Override
    public Double calculateDiscount() {
        return 0.0;
    }

    @Override
    public Double calculatePromotional() {
        double totalDiscountPromotional = 0.0;
        for (Product product : productsIsPromotional.keySet()) {
            totalDiscountPromotional += product.getPrice() * productsIsPromotional.get(product) * 0.5;
        }
        return totalDiscountPromotional;
    }
}
