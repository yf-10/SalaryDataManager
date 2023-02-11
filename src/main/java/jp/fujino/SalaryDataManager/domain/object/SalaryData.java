package jp.fujino.SalaryDataManager.domain.object;

import com.fasterxml.jackson.annotation.JsonFormat;
import jp.fujino.SalaryDataManager.infrastructure.entity.SalaryDataEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalaryData implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonFormat(pattern = "yyyy/mm/dd HH:mm:ss", timezone = "Asia/Tokyo")
    private Date createdAt;
    private String createdBy;
    @JsonFormat(pattern = "yyyy/mm/dd HH:mm:ss", timezone = "Asia/Tokyo")
    private Date updatedAt;
    private String updatedBy;
    private String month;
    private String paymentType;
    private int amount;

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
