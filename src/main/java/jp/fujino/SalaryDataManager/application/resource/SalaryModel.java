package jp.fujino.SalaryDataManager.application.resource;

import jp.fujino.SalaryDataManager.domain.object.RecordInfo;
import jp.fujino.SalaryDataManager.domain.object.Salary;
import jp.fujino.SalaryDataManager.domain.object.Money;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public record SalaryModel(
        RecordInfo recordInfo,
        String month,
        Boolean deduction,
        String paymentItem,
        Integer amount,
        String currencyCode
) implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    public static SalaryModel convertToModel(final Salary object) {
        return new SalaryModel(
                object.getRecordInfo(),
                object.getMonth(),
                object.getDeduction(),
                object.getPaymentItem(),
                object.getMoney().amount(),
                object.getMoney().currencyCode()
        );
    }

    public static List<SalaryModel> convertToModels(final List<Salary> objects) {
        List<SalaryModel> models = new ArrayList<>();
        objects.forEach(each -> models.add(convertToModel(each)));
        return models;
    }

    public Salary modelToObjectGet() {
        return new Salary(
                null,
                this.month(),
                this.deduction(),
                this.paymentItem(),
                null
        );
    }

    public Salary modelToObjectPost(final String createdBy) {
        return new Salary(
                RecordInfo.createRecord(createdBy),
                this.month(),
                this.deduction(),
                this.paymentItem(),
                new Money(
                        this.amount(),
                        this.currencyCode()
                )
        );
    }

    public Salary modelToObjectPut(final String updatedBy) {
        return new Salary(
                this.recordInfo().updateRecord(updatedBy),
                this.month(),
                this.deduction(),
                this.paymentItem(),
                new Money(
                        this.amount(),
                        this.currencyCode()
                )
        );
    }

    public Salary modelToObjectDelete() {
        return new Salary(
                null,
                this.month(),
                this.deduction(),
                this.paymentItem(),
                null
        );
    }

}
