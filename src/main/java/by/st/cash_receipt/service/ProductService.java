package by.st.cash_receipt.service;

import by.st.cash_receipt.model.Product;
import by.st.cash_receipt.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public Product getProduct(Long id) {
        return productRepository.findById(id).orElseThrow();
    }

    public List<Product> getAllProductsBy(List<Long> ids) {
        return productRepository.findAllById(ids);
    }

    public Map<Product, Integer> getProductsCount(List<Long> itemsIds) {
        List<Product> products = productRepository.findAllById(itemsIds);
        Map<Product, Integer> productIntegerMap = new HashMap<>();
        for (Product product : products) {
            int count = Collections.frequency(itemsIds, product.getId());
            productIntegerMap.put(product, count);
        }
        return productIntegerMap;
    }
}
