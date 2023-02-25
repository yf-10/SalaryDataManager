package jp.fujino.SalaryDataManager.domain.service;

import jakarta.transaction.Transactional;
import jp.fujino.SalaryDataManager.domain.object.Salary;
import jp.fujino.SalaryDataManager.domain.repository.SalaryRepository;
import jp.fujino.SalaryDataManager.infrastructure.entity.SalaryEntity;
import jp.fujino.SalaryDataManager.infrastructure.key.SalaryKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class SalaryService {

    @Autowired
    SalaryRepository salaryRepository;

    /** GET by PrimaryKey **/
    public Salary getById(final Salary object) throws Exception {
        /* Validate "Existence" */
        if (!object.exists(salaryRepository)) {
            throw new Exception("登録されていません。[" + object.getMonth() + " " + object.getPaymentItem() + "]");
        }
        /* Search */
        final SalaryKey key = new SalaryKey(object.getMonth(), object.getDeduction(), object.getPaymentItem());
        final SalaryEntity foundEntity = salaryRepository.findById(key).get();
        final Salary foundObject = new Salary(foundEntity);
        return foundObject;
    }

    /** GET by Month **/
    public List<Salary> getByMonth(final String month) {
        final List<SalaryEntity> foundEntities = salaryRepository.findByMonth(month);
        List<Salary> foundObjects = new ArrayList<>();
        foundEntities.forEach(each -> foundObjects.add(new Salary(each)));
        return foundObjects;
    }

    /** GET by Month Between **/
    public List<Salary> getByMonthBetween(final String monthFrom, final String monthTo) {
        final List<SalaryEntity> foundEntities = salaryRepository.findByMonthBetween(monthFrom, monthTo);
        List<Salary> foundObjects = new ArrayList<>();
        foundEntities.forEach(each -> foundObjects.add(new Salary(each)));
        return foundObjects;
    }

    /** GET by Month After **/
    public List<Salary> getByMonthAfter(final String monthFrom) {
        final List<SalaryEntity> foundEntities = salaryRepository.findByMonthAfter(monthFrom);
        List<Salary> foundObjects = new ArrayList<>();
        foundEntities.forEach(each -> foundObjects.add(new Salary(each)));
        return foundObjects;
    }

    /** GET by Month Before **/
    public List<Salary> getByMonthBefore(final String monthTo) {
        final List<SalaryEntity> foundEntities = salaryRepository.findByMonthBefore(monthTo);
        List<Salary> foundObjects = new ArrayList<>();
        foundEntities.forEach(each -> foundObjects.add(new Salary(each)));
        return foundObjects;
    }

    /** GET by PaymentItem **/
    public List<Salary> getByPaymentItem(final Boolean deduction, final String paymentItem) {
        final List<SalaryEntity> foundEntities = salaryRepository.findByDeductionAndPaymentItem(deduction, paymentItem);
        List<Salary> foundObjects = new ArrayList<>();
        foundEntities.forEach(each -> foundObjects.add(new Salary(each)));
        return foundObjects;
    }

    /** GET by PaymentItem In **/
    public List<Salary> getByPaymentItem(final Boolean deduction, final List<String> paymentItemList) {
        final List<SalaryEntity> foundEntities = salaryRepository.findByDeductionAndPaymentItemIn(deduction, paymentItemList);
        List<Salary> foundObjects = new ArrayList<>();
        foundEntities.forEach(each -> foundObjects.add(new Salary(each)));
        return foundObjects;
    }

    /** POST **/
    public Salary post(final Salary object, final boolean force) throws Exception {
        /* Validate "Existence" */
        if (!force && object.exists(salaryRepository)) {
            throw new Exception("登録済みです。[" + object.getMonth() + " " + object.getPaymentItem() + "]");
        }
        /* Save */
        final SalaryEntity saveEntity = object.convertToEntity();
        final Salary saveObject = new Salary(salaryRepository.save(saveEntity));
        return saveObject;
    }

    /** POST List **/
    public List<Salary> post(final List<Salary> objects, final boolean force) throws Exception {
        /* Validate "Existence" */
        if (!force) {
            for (Salary object : objects) {
                if (object.exists(salaryRepository)) {
                    throw new Exception("登録済みのデータが存在します。");
                }
            }
        }
        /* Save */
        List<SalaryEntity> entities = new ArrayList<>();
        objects.forEach(each -> {entities.add(each.convertToEntity());});
        final List<SalaryEntity> saveEntities = salaryRepository.saveAll(entities);
        List<Salary> saveObjects = new ArrayList<>();
        saveEntities.forEach(each -> saveObjects.add(new Salary(each)));
        return saveObjects;
    }

    /** PUT **/
    public Salary put(final Salary object) throws Exception {
        /* Validate "Existence" */
        if (!object.exists(salaryRepository)) {
            throw new Exception("登録されていません。[" + object.getMonth() + " " + object.getPaymentItem() + "]");
        }
        /* Validate "Exclusive flag" */
        final Salary origin = this.getById(object);
        if (object.getRecordInfo().exclusiveFlag() != origin.getRecordInfo().exclusiveFlag()) {
            throw new RuntimeException("他の人が更新しています。[" + object.getMonth() + " " + object.getPaymentItem() + "]");
        }
        /* Save */
        final SalaryEntity entity = object.countUpExclusiveFlag().convertToEntity();
        final Salary saveObject = new Salary(salaryRepository.save(entity));
        return saveObject;
    }

    /** DELETE **/
    public Salary delete(final Salary object) throws Exception {
        /* Validate "Existence" */
        if (!object.exists(salaryRepository)) {
            throw new Exception("登録されていません。[" + object.getMonth() + " " + object.getPaymentItem() + "]");
        }
        /* Delete */
        SalaryEntity entity = this.getById(object).convertToEntity();
        salaryRepository.delete(entity);
        Salary deleteObject = new Salary(entity);
        return deleteObject;
    }

}
