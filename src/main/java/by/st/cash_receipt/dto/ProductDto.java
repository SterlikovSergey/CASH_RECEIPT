package by.st.cash_receipt.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@ToString
public class ProductDto {
    private String description;
    private Double price;
    private Boolean isPromotional;
}
