package jp.fujino.SalaryDataManager.domain.object;

import com.fasterxml.jackson.annotation.JsonFormat;
import jp.fujino.SalaryDataManager.infrastructure.entity.SalaryDataEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SalaryData implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonFormat(pattern = "yyyy/mm/dd HH:mm:ss", timezone = "Asia/Tokyo")
    private final Date createdAt;
    private final String createdBy;
    @JsonFormat(pattern = "yyyy/mm/dd HH:mm:ss", timezone = "Asia/Tokyo")
    private final Date updatedAt;
    private final String updatedBy;
    private final String month;
    private final String paymentType;
    private final int amount;

    /** Constructor **/
    public SalaryData(SalaryDataEntity entity) {
        this.createdAt = entity.getCreatedAt();
        this.createdBy = entity.getCreatedBy();
        this.updatedAt = entity.getUpdatedAt();
        this.updatedBy = entity.getUpdatedBy();
        this.month = entity.getMonth();
        this.paymentType = entity.getPaymentType();
        this.amount = entity.getAmount();
    }

}
