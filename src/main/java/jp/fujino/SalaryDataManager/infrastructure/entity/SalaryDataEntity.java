package jp.fujino.SalaryDataManager.infrastructure.entity;

import jakarta.persistence.*;
import jp.fujino.SalaryDataManager.infrastructure.key.SalaryDataKey;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name = "salary")
@IdClass(value = SalaryDataKey.class)
public class SalaryDataEntity extends CommonEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "month")
    private String month;

    @Id
    @Column(name = "payment_type")
    private String paymentType;

    @Column(name = "amount")
    private int amount;

}
