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

    private Double totalPrice; //Общая цена

    private Double totalDiscount;  //Общая скидка

    private Double totalPriceWithDiscount; //Общая цена со скидкой

}
