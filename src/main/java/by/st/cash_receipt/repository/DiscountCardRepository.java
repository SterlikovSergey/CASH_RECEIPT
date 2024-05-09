package by.st.cash_receipt.repository;

import by.st.cash_receipt.model.DiscountCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountCardRepository extends JpaRepository<DiscountCard,Long> {

}
