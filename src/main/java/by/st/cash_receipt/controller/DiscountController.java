package by.st.cash_receipt.controller;

import by.st.cash_receipt.dto.DiscountCardDto;
import by.st.cash_receipt.mapper.DiscountCardMapper;
import by.st.cash_receipt.service.DiscountCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/discount")
public class DiscountController {
    private final DiscountCardService discountCardService;
    private final DiscountCardMapper discountCardMapper;
    @PostMapping
    public ResponseEntity<Object> addDiscount(@RequestBody DiscountCardDto dto){
        return new ResponseEntity<>(discountCardService.addDiscountCard(discountCardMapper.toDiscountCard(dto)),
                HttpStatus.CREATED);
    }
}
