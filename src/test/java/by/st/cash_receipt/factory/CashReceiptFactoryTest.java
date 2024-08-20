package by.st.cash_receipt.factory;

import static org.junit.jupiter.api.Assertions.*;

import by.st.cash_receipt.model.CashReceipt;
import by.st.cash_receipt.model.DiscountCard;
import by.st.cash_receipt.model.Product;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class CashReceiptFactoryTest {

    @Test
    public void test_create_cash_receipt_with_valid_products_and_discount_card() {
        // Arrange
        Product product1 = Product.builder().id(1L).description("Product 1").price(10.0).isPromotional(false).build();
        Product product2 = Product.builder().id(2L).description("Product 2").price(20.0).isPromotional(true).build();
        Map<Product, Integer> productIntegerMap = new HashMap<>();
        productIntegerMap.put(product1, 3);
        productIntegerMap.put(product2, 6);

        DiscountCard discountCard = DiscountCard.builder().id(1L).discountRate(10.0).build();

        CashReceiptFactory cashReceiptFactory = new CashReceiptFactory();

        // Act
        CashReceipt cashReceipt = cashReceiptFactory.create(productIntegerMap, discountCard);

        // Assert
        assertNotNull(cashReceipt);
        assertEquals("cash_receipt", cashReceipt.getName());
        assertEquals(LocalDate.now(), cashReceipt.getDate());
        assertEquals(LocalTime.now().getHour(), cashReceipt.getTime().getHour());
        assertEquals(LocalTime.now().getMinute(), cashReceipt.getTime().getMinute());
        assertEquals(discountCard, cashReceipt.getDiscountCard());
        assertEquals(productIntegerMap, cashReceipt.getProducts());
        assertEquals(150.0, cashReceipt.getTotalPrice()); // 3*10 + 6*20
        assertEquals(15.0, cashReceipt.getTotalDiscount()); // 150 * 10%
        assertEquals(60.0, cashReceipt.getTotalDiscountPromotional()); // 6*20*50%
        assertEquals(75.0, cashReceipt.getTotalPriceWithDiscount()); // 150 - 15 - 60
    }
}
