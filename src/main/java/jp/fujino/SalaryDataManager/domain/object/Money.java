package jp.fujino.SalaryDataManager.domain.object;

import java.io.Serializable;
import java.util.Currency;

public record Money(int amount, String currencyCode) implements Serializable {
    private static final long serialVersionUID = 1L;

    /** Constructor **/
    public Money {
        // "amount" Validation
        if (amount < 0) {
            throw new IllegalArgumentException("Amount must be larger than 0");
        }
        // "currencyCode" Validation
        try {
            Currency.getInstance(currencyCode);
        } catch (Exception e) {
            throw new IllegalArgumentException("Currency code abides by ISO-4217");
        }
    }

}
