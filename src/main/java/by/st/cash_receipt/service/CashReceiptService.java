package by.st.cash_receipt.service;

import by.st.cash_receipt.model.CashReceipt;
import by.st.cash_receipt.model.DiscountCard;
import by.st.cash_receipt.model.Product;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CashReceiptService {
    public void createCashReceipt(Map<Product, Long> productLongMap, DiscountCard discountCard) {
    }

    public CashReceipt calculateTotalPrice(Map<Product, Integer> productLongMap, DiscountCard discountCard) {
        double totalPrice = 0.0;
        double totalDiscount = 0.0;
        double totalPriceWithDiscount = 0.0;
        for (Product product : productLongMap.keySet()) {
            totalPrice += product.getPrice() * productLongMap.get(product);
        }
        if (discountCard != null) {
            totalPrice -= totalPrice * (discountCard.getDiscountRate() / 100);
        }
        return CashReceipt.builder()
                .totalPrice(totalPrice)
                .totalDiscount(totalDiscount)
                .totalPriceWithDiscount(totalPriceWithDiscount)
                .build();
    }
}
