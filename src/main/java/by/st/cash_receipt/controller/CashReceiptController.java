package by.st.cash_receipt.controller;

import by.st.cash_receipt.factory.CashReceiptFactory;
import by.st.cash_receipt.model.CashReceipt;
import by.st.cash_receipt.model.DiscountCard;
import by.st.cash_receipt.model.Product;
import by.st.cash_receipt.service.DiscountCardService;
import by.st.cash_receipt.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/cash_receipt")
public class CashReceiptController {
    private final ProductService productService;
    private final DiscountCardService discountCardService;
    private final CashReceiptFactory cashReceiptFactory;

    @GetMapping("/check")
    public ResponseEntity<CashReceipt> getCashReceipt(@RequestParam List<Long> itemsIds,
                                                      @RequestParam(required = false) Long discountId) {
        Map<Product, Integer> productIntegerMap = productService.getProductsCount(itemsIds);
        DiscountCard discountCard = discountId == null ? null : discountCardService.getDiscountCardBy(discountId);
        log.info("productIntegerMap: {}", productIntegerMap);
        log.info("discountCard: {}", discountCard);
        CashReceipt cashReceipt = cashReceiptFactory.create(productIntegerMap, discountCard);
        return ResponseEntity.ok(cashReceipt);
    }
}
