package jp.fujino.SalaryDataManager.application.resource;

import jp.fujino.SalaryDataManager.domain.object.Salary;
import jp.fujino.SalaryDataManager.domain.object.Money;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public record SalaryModel(
        Date createdAt,
        String createdBy,
        Date updatedAt,
        String updatedBy,
        Integer exclusiveFlag,
        String month,
        Boolean deduction,
        String paymentItem,
        Money money
) implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    public static SalaryModel convToModel(final Salary object) {
        return new SalaryModel(
                object.getCreatedAt(),
                object.getCreatedBy(),
                object.getUpdatedAt(),
                object.getUpdatedBy(),
                object.getExclusiveFlag(),
                object.getMonth(),
                object.getDeduction(),
                object.getPaymentItem(),
                object.getMoney()
        );
    }

    public static List<SalaryModel> convToModels(final List<Salary> objects) {
        List<SalaryModel> models = new ArrayList<>();
        objects.forEach(each -> models.add(convToModel(each)));
        return models;
    }

    public Salary convToObjForGet() {
        return new Salary(
                this.createdAt(),
                this.createdBy(),
                this.updatedAt(),
                this.updatedBy(),
                this.exclusiveFlag(),
                this.month(),
                this.deduction(),
                this.paymentItem(),
                this.money()
        );
    }

    public Salary convToObjectsForGet() {
        return new Salary(
                this.createdAt(),
                this.createdBy(),
                this.updatedAt(),
                this.updatedBy(),
                this.exclusiveFlag(),
                this.month(),
                this.deduction(),
                this.paymentItem(),
                this.money()
        );
    }

    public Salary convToObjForPost(final String createdBy) {
        final Date timestamp = new Date();
        return new Salary(
                timestamp,
                createdBy,
                timestamp,
                createdBy,
                0,
                this.month(),
                this.deduction(),
                this.paymentItem(),
                this.money()
        );
    }

    public Salary convToObjForPut(final String updatedBy) {
        final Date timestamp = new Date();
        return new Salary(
                this.createdAt(),
                this.createdBy(),
                timestamp,
                updatedBy,
                this.exclusiveFlag(),
                this.month(),
                this.deduction(),
                this.paymentItem(),
                this.money()
        );
    }

    public Salary convToObjForDelete() {
        return new Salary(
                this.createdAt(),
                this.createdBy(),
                this.updatedAt(),
                this.updatedBy(),
                this.exclusiveFlag(),
                this.month(),
                this.deduction(),
                this.paymentItem(),
                this.money()
        );
    }

}
