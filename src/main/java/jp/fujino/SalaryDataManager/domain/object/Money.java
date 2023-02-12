package jp.fujino.SalaryDataManager.domain.object;

import lombok.Data;

import java.util.Currency;

@Data
public record Money(int amount, Currency currency) {
    private static final long serialVersionUID = 1L;

    /** Constructor **/
    public Money {
        // Validation
        if (amount < 0) {
            throw new IllegalArgumentException("Amount must be larger than 0.");
        }
    }

}
