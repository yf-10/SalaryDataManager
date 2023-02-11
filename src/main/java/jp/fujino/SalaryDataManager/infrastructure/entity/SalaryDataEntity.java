package jp.fujino.SalaryDataManager.infrastructure.entity;

import jakarta.persistence.*;
import jp.fujino.SalaryDataManager.infrastructure.key.SalaryDataKey;
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
    private Date createdAt;

    @Column(name = "created_by")
    private String createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "updated_by")
    private String updatedBy;

    @Id
    @Column(name = "month")
    private String month;

    @Id
    @Column(name = "payment_type")
    private String paymentType;

    @Column(name = "amount")
    private int amount;

}
