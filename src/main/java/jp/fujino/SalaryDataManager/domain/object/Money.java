package jp.fujino.SalaryDataManager.domain.object;

import java.io.Serial;
import java.io.Serializable;
import java.util.Currency;

public record Money(
        Integer amount,
        String currencyCode
) implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /** Constructor **/
    public Money {
        // Validate "amount"
        if (amount < 0) {
            throw new IllegalArgumentException("Amount must be larger than 0");
        }
        // Validate "currencyCode"
        try {
            Currency.getInstance(currencyCode);
        } catch (Exception e) {
            throw new IllegalArgumentException("Currency code abides by ISO-4217");
        }
    }

}
