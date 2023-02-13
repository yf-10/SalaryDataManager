package jp.fujino.SalaryDataManager.infrastructure.key;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalaryDeductionKey implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String month;
    private String deductionType;

}
