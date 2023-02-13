package jp.fujino.SalaryDataManager.domain.service;

import jakarta.transaction.Transactional;
import jp.fujino.SalaryDataManager.application.resource.SalaryAdd;
import jp.fujino.SalaryDataManager.domain.object.Salary;
import jp.fujino.SalaryDataManager.domain.repository.SalaryRepository;
import jp.fujino.SalaryDataManager.infrastructure.entity.SalaryEntity;
import jp.fujino.SalaryDataManager.infrastructure.key.SalaryKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SalaryService {

    @Autowired
    SalaryRepository salaryRepository;

    /**
     * CRUD: Read
     */

    /** Find by PrimaryKey **/
    public Salary findById(final String month, final String paymentType) {
        final SalaryKey key = new SalaryKey(month, paymentType);
        final Optional<SalaryEntity> entity = salaryRepository.findById(key);
        return new Salary(entity.get());
    }

    /** Exists by PrimaryKey **/
    public boolean existsById(final String month, final String paymentType) {
        final SalaryKey key = new SalaryKey(month, paymentType);
        return salaryRepository.existsById(key);
    }

    /** Find by Month **/
    public List<Salary> findByMonth(final String month) {
        List<Salary> objects = new ArrayList<>();
        final List<SalaryEntity> entities = salaryRepository.findByMonth(month);
        for (final SalaryEntity entity : entities)
            objects.add(new Salary(entity));
        return objects;
    }

    /** Find by Month Between **/
    public List<Salary> findByMonthBetween(final String from, final String to) {
        List<Salary> objects = new ArrayList<>();
        final List<SalaryEntity> entities = salaryRepository.findByMonthBetween(from, to);
        for (final SalaryEntity entity : entities)
            objects.add(new Salary(entity));
        return objects;
    }

    /** Find by Month After **/
    public List<Salary> findByMonthAfter(final String month) {
        List<Salary> objects = new ArrayList<>();
        final List<SalaryEntity> entities = salaryRepository.findByMonthAfter(month);
        for (final SalaryEntity entity : entities)
            objects.add(new Salary(entity));
        return objects;
    }

    /** Find by Month Before **/
    public List<Salary> findByMonthBefore(final String month) {
        List<Salary> objects = new ArrayList<>();
        final List<SalaryEntity> entities = salaryRepository.findByMonthBefore(month);
        for (final SalaryEntity entity : entities)
            objects.add(new Salary(entity));
        return objects;
    }

    /** Find by PaymentType **/
    public List<Salary> findByPaymentType(final String paymentType) {
        List<Salary> objects = new ArrayList<>();
        final List<SalaryEntity> entities = salaryRepository.findByPaymentType(paymentType);
        for (final SalaryEntity entity : entities)
            objects.add(new Salary(entity));
        return objects;
    }

    /** Find by PaymentType In **/
    public List<Salary> findByPaymentTypeIn(final List<String> paymentType) {
        List<Salary> objects = new ArrayList<>();
        final List<SalaryEntity> entities = salaryRepository.findByPaymentTypeIn(paymentType);
        for (final SalaryEntity entity : entities)
            objects.add(new Salary(entity));
        return objects;
    }

    /**
     * CRUD: Create
     */

    /** Add **/
    public Salary add(
            final String createdBy,
            final SalaryAdd data
    ) throws Exception {
        final Date timestamp = new Date();
        final Salary object = new Salary(
                timestamp,
                createdBy,
                timestamp,
                createdBy,
                0,
                data.month(),
                data.paymentType(),
                data.money()
        );
        final SalaryEntity entity = object.covertToEntity();
        // Validate "Existence"
        if (existsById(entity.getMonth(), entity.getPaymentType())) {
            throw new Exception("登録済みです。[" + entity.getMonth() + " " + entity.getPaymentType() + "]");
        }
        return new Salary(salaryRepository.save(entity));
    }

    /** Add List **/
    private boolean validateExistence(final List<SalaryAdd> dataList) {
        for (SalaryAdd data : dataList) {
            // Validate "Existence"
            if (existsById(data.month(), data.paymentType())) {
                return false;
            }
        }
        return true;
    }
    public List<Salary> addList(
            final String createdBy,
            final List<SalaryAdd> dataList
    ) throws Exception {
        final Date timestamp = new Date();
        List<Salary> objects = new ArrayList<>();
        // Validate list
        if (!validateExistence(dataList))
            throw new Exception("登録済みのデータが存在します。");
        for (SalaryAdd data : dataList) {
            final Salary object = new Salary(
                    timestamp,
                    createdBy,
                    timestamp,
                    createdBy,
                    0,
                    data.month(),
                    data.paymentType(),
                    data.money()
            );
            final SalaryEntity entity = object.covertToEntity();
            // Save
            salaryRepository.save(entity);
            objects.add(object);
        }
        return objects;
    }

    /** Add List (Force) **/
    public List<Salary> addListForce(
            final String createdBy,
            final List<SalaryAdd> dataList
    ) throws Exception {
        final Date timestamp = new Date();
        List<Salary> objects = new ArrayList<>();
        for (SalaryAdd data : dataList) {
            final Salary object = new Salary(
                    timestamp,
                    createdBy,
                    timestamp,
                    createdBy,
                    0,
                    data.month(),
                    data.paymentType(),
                    data.money()
            );
            final SalaryEntity entity = object.covertToEntity();
            // Save
            salaryRepository.save(entity);
            objects.add(object);
        }
        return objects;
    }

    /**
     * CRUD: Update
     */

    /** Save **/
    public Salary save(
            final String updatedBy,
            final Salary data
    ) throws Exception {
        final Date timestamp = new Date();
        final Salary object = new Salary(
                data.getCreatedAt(),
                data.getCreatedBy(),
                timestamp,
                updatedBy,
                data.getExclusiveFlag() + 1,
                data.getMonth(),
                data.getPaymentType(),
                data.getMoney()
        );
        final SalaryEntity entity = object.covertToEntity();
        // Validate "Existence"
        if (!existsById(entity.getMonth(), entity.getPaymentType())) {
            throw new Exception("登録されていません。[" + entity.getMonth() + " " + entity.getPaymentType() + "]");
        }
        // Validate "Exclusion"
        final SalaryEntity origin = findById(entity.getMonth(), entity.getPaymentType()).covertToEntity();
        if (data.getExclusiveFlag() != origin.getExclusiveFlag()) {
            throw new RuntimeException("他の人が更新しています。[" + entity.getMonth() + " " + entity.getPaymentType() + "]");
        }
        // Save
        return new Salary(salaryRepository.save(entity));
    }

    /**
     * CRUD: Delete
     */

    /** Delete By PrimaryKey **/
    public Salary deleteById(final String month, final String paymentType) throws Exception {
        // Validate "Existence"
        if (!existsById(month, paymentType)) {
            throw new Exception("登録されていません。[" + month + " " + paymentType + "]");
        }
        // Delete
        SalaryEntity entity = findById(month, paymentType).covertToEntity();
        salaryRepository.delete(entity);
        return new Salary(entity);
    }

}
