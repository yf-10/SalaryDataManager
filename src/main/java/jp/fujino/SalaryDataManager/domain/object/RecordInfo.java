package jp.fujino.SalaryDataManager.domain.object;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

public record RecordInfo(
        @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss.SSS", timezone = "Asia/Tokyo")
        Date createdAt,
        String createdBy,
        @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss.SSS", timezone = "Asia/Tokyo")
        Date updatedAt,
        String updatedBy,
        Integer exclusiveFlag
) implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /** Constructor **/
    public RecordInfo {
        // Validate

    }

    /** Method **/
    public RecordInfo countUpExclusiveFlag() {
        return new RecordInfo(
                this.createdAt(),
                this.createdBy(),
                this.updatedAt(),
                this.updatedBy(),
                this.exclusiveFlag() + 1
        );
    }

    public static RecordInfo createRecord(final String createdBy) {
        final Date timestamp = new Date();
        return new RecordInfo(
                timestamp,
                createdBy,
                timestamp,
                createdBy,
                0
        );
    }

    public RecordInfo updateRecord(String updatedBy) {
        final Date timestamp = new Date();
        return new RecordInfo(
                this.createdAt(),
                this.createdBy(),
                timestamp,
                updatedBy,
                this.exclusiveFlag()
        );
    }

}
