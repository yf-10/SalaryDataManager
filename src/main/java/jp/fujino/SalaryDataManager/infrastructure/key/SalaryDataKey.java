package jp.fujino.SalaryDataManager.infrastructure.key;

import lombok.Data;

import java.io.Serializable;

@Data
public class SalaryDataKey implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String month;
    private final String paymentType;

}
