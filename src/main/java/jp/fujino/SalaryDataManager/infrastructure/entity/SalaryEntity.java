package jp.fujino.SalaryDataManager.infrastructure.entity;

import jakarta.persistence.*;
import jp.fujino.SalaryDataManager.infrastructure.key.SalaryKey;
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
@IdClass(value = SalaryKey.class)
public class SalaryEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    @Column(name = "created_by", nullable = false)
    private String createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", nullable = false)
    private Date updatedAt;

    @Column(name = "updated_by", nullable = false)
    private String updatedBy;

    @Column(name = "exclusive_flag", nullable = false)
    private Integer exclusiveFlag;

    @Id
    @Column(name = "month", nullable = false)
    private String month;

    @Id
    @Column(name = "deduction", nullable = false)
    private Boolean deduction;

    @Id
    @Column(name = "payment_item", nullable = false)
    private String paymentItem;

    @Column(name = "amount", nullable = false)
    private Integer amount;

    @Column(name = "currency_code", nullable = false)
    private String currencyCode;

}
