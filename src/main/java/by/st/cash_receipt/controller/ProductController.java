package by.st.cash_receipt.controller;

import by.st.cash_receipt.dto.ProductDto;
import by.st.cash_receipt.mapper.ProductMapper;
import by.st.cash_receipt.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/product")
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;
    @PostMapping
    public ResponseEntity<Object> addProduct(@RequestBody  ProductDto dto){
        return new ResponseEntity<>(productService.add(productMapper.toProduct(dto)),
                HttpStatus.CREATED);
    }
}
