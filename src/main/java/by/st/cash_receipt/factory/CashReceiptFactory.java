package by.st.cash_receipt.factory;

import by.st.cash_receipt.model.CashReceipt;
import by.st.cash_receipt.model.DiscountCard;
import by.st.cash_receipt.model.Product;
import by.st.cash_receipt.service.BasePriceCalculatorImpl;
import by.st.cash_receipt.service.DiscountPriceCalculatorImpl;
import by.st.cash_receipt.service.ProductService;
import by.st.cash_receipt.service.impl.PriceCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;
@Component
@RequiredArgsConstructor
public class CashReceiptFactory {
    private final ProductService productService;
    public CashReceipt create(Map<Product,Integer> productIntegerMap, DiscountCard discountCard){
        PriceCalculator priceCalculator = new BasePriceCalculatorImpl(productIntegerMap);
        if(discountCard == null){
            priceCalculator = new DiscountPriceCalculatorImpl(priceCalculator,discountCard);
        }
        double totalPrice = priceCalculator.calculatePrice();
        double totalDiscount = priceCalculator.calculateDiscount();
        double totalPriceWithDiscount = totalPrice - totalDiscount;

        return  CashReceipt.builder()
                .date(LocalDate.now())
                .time(LocalTime.now())
                .discountCard(discountCard)
                .products(productIntegerMap)
                .totalPrice(totalPrice)
                .totalDiscount(totalDiscount)
                .totalPriceWithDiscount(totalPriceWithDiscount)
                .build();
    }
}
