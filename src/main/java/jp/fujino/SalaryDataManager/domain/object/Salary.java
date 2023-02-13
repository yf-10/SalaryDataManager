package jp.fujino.SalaryDataManager.domain.object;

import com.fasterxml.jackson.annotation.JsonFormat;
import jp.fujino.SalaryDataManager.infrastructure.entity.SalaryEntity;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class Salary implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /** Field **/
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss.SSS", timezone = "Asia/Tokyo")
    private final Date createdAt;
    private final String createdBy;
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss.SSS", timezone = "Asia/Tokyo")
    private final Date updatedAt;
    private final String updatedBy;
    private final int exclusiveFlag;
    private final String month;
    private final String paymentType;
    private final Money money;

    /** Constructor **/
    public Salary(
            final SalaryEntity entity
    ) throws IllegalArgumentException {
        // "month" Validation
        this.validateMonth(entity.getMonth());
        // Initialize
        this.createdAt = entity.getCreatedAt();
        this.createdBy = entity.getCreatedBy();
        this.updatedAt = entity.getUpdatedAt();
        this.updatedBy = entity.getUpdatedBy();
        this.exclusiveFlag = entity.getExclusiveFlag();
        this.month = entity.getMonth();
        this.paymentType = entity.getPaymentType();
        this.money = new Money(entity.getAmount(), entity.getCurrencyCode());
    }

    public Salary(
            final Date createdAt,
            final String createdBy,
            final Date updatedAt,
            final String updatedBy,
            final int exclusiveFlag,
            final String month,
            final String paymentType,
            final Money money
    ) throws IllegalArgumentException {
        // "month" Validation
        this.validateMonth(month);
        // Initialize
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.updatedAt = updatedAt;
        this.updatedBy = updatedBy;
        this.exclusiveFlag = exclusiveFlag;
        this.month = month;
        this.paymentType = paymentType;
        this.money = money;
    }

    /** Method **/
    private void validateMonth(final String month) {
        try {
            (new SimpleDateFormat("yyyyMM")).parse(month);
        } catch (Exception e) {
            throw new IllegalArgumentException("フォーマットが不正です。[month:YYYYMM]");
        }
    }

    public SalaryEntity covertToEntity() {
        return new SalaryEntity(
                this.getCreatedAt(),
                this.getCreatedBy(),
                this.getUpdatedAt(),
                this.getUpdatedBy(),
                this.getExclusiveFlag(),
                this.getMonth(),
                this.getPaymentType(),
                this.getMoney().amount(),
                this.getMoney().currencyCode()
        );
    }

}
