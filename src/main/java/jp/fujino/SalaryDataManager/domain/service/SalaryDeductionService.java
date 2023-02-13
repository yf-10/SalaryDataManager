package jp.fujino.SalaryDataManager.domain.service;

import jakarta.transaction.Transactional;
import jp.fujino.SalaryDataManager.application.resource.SalaryDeductionInput;
import jp.fujino.SalaryDataManager.domain.object.SalaryDeduction;
import jp.fujino.SalaryDataManager.domain.repository.SalaryDeductionRepository;
import jp.fujino.SalaryDataManager.infrastructure.entity.SalaryDeductionEntity;
import jp.fujino.SalaryDataManager.infrastructure.key.SalaryDeductionKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SalaryDeductionService {

    @Autowired
    SalaryDeductionRepository salaryDeductionRepository;

    /**
     * CRUD: Read
     */

    /** Find by PrimaryKey **/
    public SalaryDeduction findById(final String month, final String deductionType) {
        final SalaryDeductionKey key = new SalaryDeductionKey(month, deductionType);
        final Optional<SalaryDeductionEntity> entity = salaryDeductionRepository.findById(key);
        return new SalaryDeduction(entity.get());
    }

    /** Exists by PrimaryKey **/
    public boolean existsById(final String month, final String deductionType) {
        final SalaryDeductionKey key = new SalaryDeductionKey(month, deductionType);
        return salaryDeductionRepository.existsById(key);
    }

    /** Find by Month **/
    public List<SalaryDeduction> findByMonth(final String month) {
        List<SalaryDeduction> objects = new ArrayList<>();
        final List<SalaryDeductionEntity> entities = salaryDeductionRepository.findByMonth(month);
        for (final SalaryDeductionEntity entity : entities)
            objects.add(new SalaryDeduction(entity));
        return objects;
    }

    /** Find by Month Between **/
    public List<SalaryDeduction> findByMonthBetween(final String from, final String to) {
        List<SalaryDeduction> objects = new ArrayList<>();
        final List<SalaryDeductionEntity> entities = salaryDeductionRepository.findByMonthBetween(from, to);
        for (final SalaryDeductionEntity entity : entities)
            objects.add(new SalaryDeduction(entity));
        return objects;
    }

    /** Find by Month After **/
    public List<SalaryDeduction> findByMonthAfter(final String month) {
        List<SalaryDeduction> objects = new ArrayList<>();
        final List<SalaryDeductionEntity> entities = salaryDeductionRepository.findByMonthAfter(month);
        for (final SalaryDeductionEntity entity : entities)
            objects.add(new SalaryDeduction(entity));
        return objects;
    }

    /** Find by Month Before **/
    public List<SalaryDeduction> findByMonthBefore(final String month) {
        List<SalaryDeduction> objects = new ArrayList<>();
        final List<SalaryDeductionEntity> entities = salaryDeductionRepository.findByMonthBefore(month);
        for (final SalaryDeductionEntity entity : entities)
            objects.add(new SalaryDeduction(entity));
        return objects;
    }

    /** Find by DeductionType **/
    public List<SalaryDeduction> findByDeductionType(final String deductionType) {
        List<SalaryDeduction> objects = new ArrayList<>();
        final List<SalaryDeductionEntity> entities = salaryDeductionRepository.findByDeductionType(deductionType);
        for (final SalaryDeductionEntity entity : entities)
            objects.add(new SalaryDeduction(entity));
        return objects;
    }

    /** Find by DeductionType In **/
    public List<SalaryDeduction> findByDeductionTypeIn(final List<String> deductionType) {
        List<SalaryDeduction> objects = new ArrayList<>();
        final List<SalaryDeductionEntity> entities = salaryDeductionRepository.findByDeductionTypeIn(deductionType);
        for (final SalaryDeductionEntity entity : entities)
            objects.add(new SalaryDeduction(entity));
        return objects;
    }

    /**
     * CRUD: Create
     */

    /** Add **/
    public SalaryDeduction add(
            final String createdBy,
            final SalaryDeductionInput data
    ) throws Exception {
        final Date timestamp = new Date();
        final SalaryDeduction object = new SalaryDeduction(
                timestamp,
                createdBy,
                timestamp,
                createdBy,
                0,
                data.month(),
                data.deductionType(),
                data.money()
        );
        final SalaryDeductionEntity entity = object.covertToEntity();
        // Validate "Existence"
        if (existsById(entity.getMonth(), entity.getDeductionType())) {
            throw new Exception("登録済みです。[" + entity.getMonth() + " " + entity.getDeductionType() + "]");
        }
        return new SalaryDeduction(salaryDeductionRepository.save(entity));
    }

    /** Add List **/
    private boolean validateExistence(final List<SalaryDeductionInput> dataList) {
        for (SalaryDeductionInput data : dataList) {
            // Validate "Existence"
            if (existsById(data.month(), data.deductionType())) {
                return false;
            }
        }
        return true;
    }
    public List<SalaryDeduction> addList(
            final String createdBy,
            final List<SalaryDeductionInput> dataList
    ) throws Exception {
        final Date timestamp = new Date();
        List<SalaryDeduction> objects = new ArrayList<>();
        // Validate list
        if (!validateExistence(dataList))
            throw new Exception("登録済みのデータが存在します。");
        for (SalaryDeductionInput data : dataList) {
            final SalaryDeduction object = new SalaryDeduction(
                    timestamp,
                    createdBy,
                    timestamp,
                    createdBy,
                    0,
                    data.month(),
                    data.deductionType(),
                    data.money()
            );
            final SalaryDeductionEntity entity = object.covertToEntity();
            // Save
            salaryDeductionRepository.save(entity);
            objects.add(object);
        }
        return objects;
    }

    /** Add List (Force) **/
    public List<SalaryDeduction> addListForce(
            final String createdBy,
            final List<SalaryDeductionInput> dataList
    ) throws Exception {
        final Date timestamp = new Date();
        List<SalaryDeduction> objects = new ArrayList<>();
        for (SalaryDeductionInput data : dataList) {
            final SalaryDeduction object = new SalaryDeduction(
                    timestamp,
                    createdBy,
                    timestamp,
                    createdBy,
                    0,
                    data.month(),
                    data.deductionType(),
                    data.money()
            );
            final SalaryDeductionEntity entity = object.covertToEntity();
            // Save
            salaryDeductionRepository.save(entity);
            objects.add(object);
        }
        return objects;
    }

    /**
     * CRUD: Update
     */

    /** Save **/
    public SalaryDeduction save(
            final String updatedBy,
            final SalaryDeduction data
    ) throws Exception {
        final Date timestamp = new Date();
        final SalaryDeduction object = new SalaryDeduction(
                data.getCreatedAt(),
                data.getCreatedBy(),
                timestamp,
                updatedBy,
                data.getExclusiveFlag() + 1,
                data.getMonth(),
                data.getDeductionType(),
                data.getMoney()
        );
        final SalaryDeductionEntity entity = object.covertToEntity();
        // Validate "Existence"
        if (!existsById(entity.getMonth(), entity.getDeductionType())) {
            throw new Exception("登録されていません。[" + entity.getMonth() + " " + entity.getDeductionType() + "]");
        }
        // Validate "Exclusion"
        final SalaryDeductionEntity origin = findById(entity.getMonth(), entity.getDeductionType()).covertToEntity();
        if (data.getExclusiveFlag() != origin.getExclusiveFlag()) {
            throw new RuntimeException("他の人が更新しています。[" + entity.getMonth() + " " + entity.getDeductionType() + "]");
        }
        // Save
        return new SalaryDeduction(salaryDeductionRepository.save(entity));
    }

    /**
     * CRUD: Delete
     */

    /** Delete By PrimaryKey **/
    public SalaryDeduction deleteById(final String month, final String deductionType) throws Exception {
        // Validate "Existence"
        if (!existsById(month, deductionType)) {
            throw new Exception("登録されていません。[" + month + " " + deductionType + "]");
        }
        // Delete
        SalaryDeductionEntity entity = findById(month, deductionType).covertToEntity();
        salaryDeductionRepository.delete(entity);
        return new SalaryDeduction(entity);
    }

}
