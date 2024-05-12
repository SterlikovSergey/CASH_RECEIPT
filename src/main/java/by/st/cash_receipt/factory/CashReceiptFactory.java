package by.st.cash_receipt.factory;

import by.st.cash_receipt.model.CashReceipt;
import by.st.cash_receipt.model.DiscountCard;
import by.st.cash_receipt.model.Product;
import by.st.cash_receipt.calculator.BasePriceCalculatorImpl;
import by.st.cash_receipt.calculator.DiscountPriceCalculatorImpl;
import by.st.cash_receipt.calculator.impl.PriceCalculator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

@Component
@RequiredArgsConstructor
@Slf4j
public class CashReceiptFactory {
    public CashReceipt create(Map<Product, Integer> productIntegerMap, DiscountCard discountCard) {
        BasePriceCalculatorImpl basePriceCalculator = new BasePriceCalculatorImpl(productIntegerMap);
        PriceCalculator priceCalculator = basePriceCalculator;
        PriceCalculator discountCalculator = new DiscountPriceCalculatorImpl(priceCalculator, discountCard);
        double totalPrice = priceCalculator.calculatePrice();
        double totalDiscount = discountCalculator.calculateDiscount();
        double totalDiscountPromotional = basePriceCalculator.calculatePromotional();
        double totalPriceWithDiscount = totalPrice - totalDiscount - totalDiscountPromotional;

        log.info("totalPrice = {}", totalPrice);
        log.info("totalDiscount = {}", totalDiscount);
        log.info("totalDiscountPromotional = {}", totalDiscountPromotional);
        log.info("{} = {} - {} - {}", totalPriceWithDiscount,
                totalPrice, totalDiscount, totalDiscountPromotional);

        return CashReceipt.builder()
                .name(new String("cash_receipt"))
                .date(LocalDate.now())
                .time(LocalTime.now())
                .discountCard(discountCard)
                .products(productIntegerMap)
                .totalPrice(totalPrice)
                .totalDiscount(totalDiscount)
                .totalDiscountPromotional(totalDiscountPromotional)
                .totalPriceWithDiscount(totalPriceWithDiscount)
                .build();
    }
}
