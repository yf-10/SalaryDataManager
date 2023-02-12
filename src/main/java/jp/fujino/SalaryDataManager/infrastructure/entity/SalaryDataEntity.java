package jp.fujino.SalaryDataManager.infrastructure.entity;

import jakarta.persistence.*;
import jp.fujino.SalaryDataManager.infrastructure.key.SalaryDataKey;
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
@Table(name = "salary")
@IdClass(value = SalaryDataKey.class)
public class SalaryDataEntity implements Serializable {
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
    @Column(name = "payment_type")
    private String paymentType;

    @Column(name = "amount")
    private int amount;

    @Column(name = "currency_code")
    private String currencyCode;

}
