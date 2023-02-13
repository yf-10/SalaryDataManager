package jp.fujino.SalaryDataManager.application.resource;

import jp.fujino.SalaryDataManager.domain.object.Money;

import java.io.Serial;
import java.io.Serializable;

public record SalaryDataAdd(
        String month,
        String paymentType,
        Money money
) implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

}
