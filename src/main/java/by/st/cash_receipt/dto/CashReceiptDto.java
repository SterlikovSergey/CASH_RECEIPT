package by.st.cash_receipt.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Data
@Getter
@Setter
@ToString
@Component
public class CashReceiptDto {
    private Map<Long,Long> map;
}
