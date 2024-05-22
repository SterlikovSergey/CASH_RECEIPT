package by.st.cash_receipt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductViewController {
    @GetMapping("/products")
    public String getProductsPage() {
        return "product";
    }
}
