package jp.fujino.SalaryDataManager.infrastructure.entity;

import jakarta.persistence.*;
import jp.fujino.SalaryDataManager.infrastructure.key.SalaryDeductionKey;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "salary_deduction")
@IdClass(value = SalaryDeductionKey.class)
public class SalaryDeductionEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "created_by")
    private String createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "exclusive_flag")
    private int exclusiveFlag;

    @Id
    @Column(name = "month")
    private String month;

    @Id
    @Column(name = "deduction_type")
    private String deductionType;

    @Column(name = "amount")
    private int amount;

    @Column(name = "currency_code")
    private String currencyCode;

}
