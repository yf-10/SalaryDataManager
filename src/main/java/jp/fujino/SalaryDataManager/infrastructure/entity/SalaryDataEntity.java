package jp.fujino.SalaryDataManager.infrastructure.entity;

import jakarta.persistence.*;
import jp.fujino.SalaryDataManager.infrastructure.key.SalaryDataKey;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "salary")
@IdClass(value = SalaryDataKey.class)
public class SalaryDataEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private final Date createdAt;

    @Column(name = "created_by")
    private final String createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private final Date updatedAt;

    @Column(name = "updated_by")
    private final String updatedBy;

    @Column(name = "exclusive_flag")
    private final int exclusiveFlag;

    @Id
    @Column(name = "month")
    private final String month;

    @Id
    @Column(name = "payment_type")
    private final String paymentType;

    @Column(name = "amount")
    private final int amount;

    @Column(name = "currency")
    private final String currency;

}
