package by.st.cash_receipt.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "discount_cards")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DiscountCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double discountRate;

}

