package by.st.cash_receipt.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "cash_receipts")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CashReceipt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @DateTimeFormat(pattern = "HH:mm:ss")
    private LocalTime time;

    @ElementCollection
    private Map<Product, Integer> products;

    @ManyToOne
    private DiscountCard discountCard;

    @Column(name = "PROMOTIONAL_TOT")
    private Double totalDiscountPromotional; // сумма скидки по акционным товарам если их больше пяти

    @Column(name = "TAXABLE_TOT")
    private Double totalPrice; //Общая стоимость товаров без скидки

    @Column(name = "VAT")
    private Double totalDiscount;  //сумма скидки по скидочной карте для неаукционных товаров и акционных товаров если их меньше пяти

    @Column(name = "TOTAL")
    private Double totalPriceWithDiscount; //итоговая цена с учётом скидки по скидочной карте

}
