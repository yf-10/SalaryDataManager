package jp.fujino.SalaryDataManager.domain.object;

import com.fasterxml.jackson.annotation.JsonFormat;
import jp.fujino.SalaryDataManager.domain.repository.SalaryRepository;
import jp.fujino.SalaryDataManager.infrastructure.entity.SalaryEntity;
import jp.fujino.SalaryDataManager.infrastructure.key.SalaryKey;
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
    private final Integer exclusiveFlag;
    private final String month;
    private final Boolean deduction;
    private final String paymentItem;
    private final Money money;

    /** Constructor **/
    public Salary(
            final SalaryEntity entity
    ) throws IllegalArgumentException {
        // Validate
        this.validateMonth(entity.getMonth());
        // Initialize
        this.createdAt = entity.getCreatedAt();
        this.createdBy = entity.getCreatedBy();
        this.updatedAt = entity.getUpdatedAt();
        this.updatedBy = entity.getUpdatedBy();
        this.exclusiveFlag = entity.getExclusiveFlag();
        this.month = entity.getMonth();
        this.deduction = entity.getDeduction();
        this.paymentItem = entity.getPaymentItem();
        this.money = new Money(entity.getAmount(), entity.getCurrencyCode());
    }

    public Salary(
            final Date createdAt,
            final String createdBy,
            final Date updatedAt,
            final String updatedBy,
            final Integer exclusiveFlag,
            final String month,
            final Boolean deduction,
            final String paymentItem,
            final Money money
    ) throws IllegalArgumentException {
        // Validate
        this.validateMonth(month);
        // Initialize
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.updatedAt = updatedAt;
        this.updatedBy = updatedBy;
        this.exclusiveFlag = exclusiveFlag;
        this.month = month;
        this.deduction = deduction;
        this.paymentItem = paymentItem;
        this.money = money;
    }

    /** Method **/
    public SalaryEntity convertToEntity() {
        return new SalaryEntity(
                this.getCreatedAt(),
                this.getCreatedBy(),
                this.getUpdatedAt(),
                this.getUpdatedBy(),
                this.getExclusiveFlag(),
                this.getMonth(),
                this.getDeduction(),
                this.getPaymentItem(),
                this.getMoney().amount(),
                this.getMoney().currencyCode()
        );
    }

    public boolean exists(final SalaryRepository salaryRepository) {
        SalaryKey key = new SalaryKey(this.getMonth(), this.getDeduction(), this.getPaymentItem());
        return salaryRepository.existsById(key);
    }

    public Salary countUpExclusiveFlag() {
        return new Salary(
                this.getCreatedAt(),
                this.getCreatedBy(),
                this.getUpdatedAt(),
                this.getUpdatedBy(),
                this.getExclusiveFlag() + 1,
                this.getMonth(),
                this.getDeduction(),
                this.getPaymentItem(),
                this.getMoney()
        );
    }

    private void validateMonth(final String month) {
        try {
            (new SimpleDateFormat("yyyyMM")).parse(month);
        } catch (Exception e) {
            throw new IllegalArgumentException("フォーマットが不正です。[month:YYYYMM]");
        }
    }

}
