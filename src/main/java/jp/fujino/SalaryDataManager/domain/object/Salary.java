package jp.fujino.SalaryDataManager.domain.object;

import jp.fujino.SalaryDataManager.domain.repository.SalaryRepository;
import jp.fujino.SalaryDataManager.infrastructure.entity.SalaryEntity;
import jp.fujino.SalaryDataManager.infrastructure.key.SalaryKey;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.text.SimpleDateFormat;

@Data
public class Salary implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /** Field **/
    private final RecordInfo recordInfo;
    private final String month;
    private final Boolean deduction;
    private final String paymentItem;
    private final Money money;

    /** Constructor **/
    public Salary(final SalaryEntity entity) throws IllegalArgumentException {
        // Validate
        this.validateMonth(entity.getMonth());
        // Initialize
        this.recordInfo = new RecordInfo(
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getUpdatedAt(),
                entity.getUpdatedBy(),
                entity.getExclusiveFlag()
        );
        this.month = entity.getMonth();
        this.deduction = entity.getDeduction();
        this.paymentItem = entity.getPaymentItem();
        this.money = new Money(
                entity.getAmount(),
                entity.getCurrencyCode()
        );
    }

    public Salary(
            final RecordInfo recordInfo,
            final String month,
            final Boolean deduction,
            final String paymentItem,
            final Money money
    ) throws IllegalArgumentException {
        // Validate
        this.validateMonth(month);
        // Initialize
        this.recordInfo = recordInfo;
        this.month = month;
        this.deduction = deduction;
        this.paymentItem = paymentItem;
        this.money = money;
    }

    /** Method **/
    public SalaryEntity convertToEntity() {
        return new SalaryEntity(
                this.getRecordInfo().createdAt(),
                this.getRecordInfo().createdBy(),
                this.getRecordInfo().updatedAt(),
                this.getRecordInfo().updatedBy(),
                this.getRecordInfo().exclusiveFlag(),
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
                this.getRecordInfo().countUpExclusiveFlag(),
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
