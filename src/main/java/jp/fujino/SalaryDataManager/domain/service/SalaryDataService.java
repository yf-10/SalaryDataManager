package jp.fujino.SalaryDataManager.domain.service;

import jakarta.transaction.Transactional;
import jp.fujino.SalaryDataManager.application.resource.SalaryDataAdd;
import jp.fujino.SalaryDataManager.domain.object.SalaryData;
import jp.fujino.SalaryDataManager.domain.repository.SalaryDataRepository;
import jp.fujino.SalaryDataManager.infrastructure.entity.SalaryDataEntity;
import jp.fujino.SalaryDataManager.infrastructure.key.SalaryDataKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SalaryDataService {

    @Autowired
    SalaryDataRepository salaryDataRepository;

    /**
     * CRUD: Read
     */

    /** Find by PrimaryKey **/
    public SalaryData findById(final String month, final String paymentType) {
        final SalaryDataKey key = new SalaryDataKey(month, paymentType);
        final Optional<SalaryDataEntity> entity = salaryDataRepository.findById(key);
        return new SalaryData(entity.get());
    }

    /** Exists by PrimaryKey **/
    public boolean existsById(final String month, final String paymentType) {
        final SalaryDataKey key = new SalaryDataKey(month, paymentType);
        return salaryDataRepository.existsById(key);
    }

    /** Find by Month **/
    public List<SalaryData> findByMonth(final String month) {
        List<SalaryData> objects = new ArrayList<>();
        final List<SalaryDataEntity> entities = salaryDataRepository.findByMonth(month);
        for (final SalaryDataEntity entity : entities)
            objects.add(new SalaryData(entity));
        return objects;
    }

    /** Find by Month Between **/
    public List<SalaryData> findByMonthBetween(final String from, final String to) {
        List<SalaryData> objects = new ArrayList<>();
        final List<SalaryDataEntity> entities = salaryDataRepository.findByMonthBetween(from, to);
        for (final SalaryDataEntity entity : entities)
            objects.add(new SalaryData(entity));
        return objects;
    }

    /** Find by Month After **/
    public List<SalaryData> findByMonthAfter(final String month) {
        List<SalaryData> objects = new ArrayList<>();
        final List<SalaryDataEntity> entities = salaryDataRepository.findByMonthAfter(month);
        for (final SalaryDataEntity entity : entities)
            objects.add(new SalaryData(entity));
        return objects;
    }

    /** Find by Month Before **/
    public List<SalaryData> findByMonthBefore(final String month) {
        List<SalaryData> objects = new ArrayList<>();
        final List<SalaryDataEntity> entities = salaryDataRepository.findByMonthBefore(month);
        for (final SalaryDataEntity entity : entities)
            objects.add(new SalaryData(entity));
        return objects;
    }

    /** Find by PaymentType **/
    public List<SalaryData> findByPaymentType(final String paymentType) {
        List<SalaryData> objects = new ArrayList<>();
        final List<SalaryDataEntity> entities = salaryDataRepository.findByPaymentType(paymentType);
        for (final SalaryDataEntity entity : entities)
            objects.add(new SalaryData(entity));
        return objects;
    }

    /** Find by PaymentType In **/
    public List<SalaryData> findByPaymentTypeIn(final List<String> paymentType) {
        List<SalaryData> objects = new ArrayList<>();
        final List<SalaryDataEntity> entities = salaryDataRepository.findByPaymentTypeIn(paymentType);
        for (final SalaryDataEntity entity : entities)
            objects.add(new SalaryData(entity));
        return objects;
    }

    /**
     * CRUD: Create
     */

    /** Add **/
    public SalaryData add(
            final String createdBy,
            final SalaryDataAdd data
    ) throws Exception {
        final Date timestamp = new Date();
        final SalaryData object = new SalaryData(
                timestamp,
                createdBy,
                timestamp,
                createdBy,
                0,
                data.month(),
                data.paymentType(),
                data.money()
        );
        final SalaryDataEntity entity = object.covertToEntity();
        // Validate "Existence"
        if (existsById(entity.getMonth(), entity.getPaymentType())) {
            throw new Exception("登録済みです。[" + entity.getMonth() + " " + entity.getPaymentType() + "]");
        }
        return new SalaryData(salaryDataRepository.save(entity));
    }

    /** Add List **/
    public List<SalaryData> addList(
            final String createdBy,
            final List<SalaryDataAdd> dataList
    ) {
        final Date timestamp = new Date();
        List<SalaryData> objects = new ArrayList<>();
        String errorMsg = "";
        for (SalaryDataAdd data : dataList) {
            final SalaryData object = new SalaryData(
                    timestamp,
                    createdBy,
                    timestamp,
                    createdBy,
                    0,
                    data.month(),
                    data.paymentType(),
                    data.money()
            );
            final SalaryDataEntity entity = object.covertToEntity();
            // Validate "Existence"
            if (existsById(entity.getMonth(), entity.getPaymentType())) {
                errorMsg += "登録済みです。[" + entity.getMonth() + " " + entity.getPaymentType() + "]";
                errorMsg += "\n";
                continue;
            }
            // Save
            salaryDataRepository.save(object.covertToEntity());
            objects.add(object);
        }
        if (errorMsg.equals("")) throw new RuntimeException(errorMsg);
        return objects;
    }

    /**
     * CRUD: Update
     */

    /** Save **/
    public SalaryData save(
            final String updatedBy,
            final SalaryData data
    ) throws Exception {
        final Date timestamp = new Date();
        final SalaryData object = new SalaryData(
                data.getCreatedAt(),
                data.getCreatedBy(),
                timestamp,
                updatedBy,
                data.getExclusiveFlag(),
                data.getMonth(),
                data.getPaymentType(),
                data.getMoney()
        );
        final SalaryDataEntity entity = object.covertToEntity();
        // Validate "Existence"
        if (!existsById(entity.getMonth(), entity.getPaymentType())) {
            throw new Exception("登録がありません。[" + entity.getMonth() + " " + entity.getPaymentType() + "]");
        }
        // Validate "Exclusion"
        final SalaryDataEntity origin = findById(data.getMonth(), data.getPaymentType()).covertToEntity();
        if (data.getExclusiveFlag() != origin.getExclusiveFlag()) {
            throw new RuntimeException("他の人が更新しています。[" + entity.getMonth() + " " + entity.getPaymentType() + "]");
        }
        // Save
        return new SalaryData(salaryDataRepository.save(entity));
    }

    /**
     * CRUD: Delete
     */

    /** Delete By PrimaryKey **/
    public SalaryData deleteById(final String month, final String paymentType) throws Exception {
        SalaryDataKey key = new SalaryDataKey(month, paymentType);
        SalaryDataEntity entity = salaryDataRepository.findById(key).get();
        // Validate "Existence"
        if (!existsById(entity.getMonth(), entity.getPaymentType())) {
            throw new Exception("登録がありません。[" + entity.getMonth() + " " + entity.getPaymentType() + "]");
        }
        // Delete
        salaryDataRepository.delete(entity);
        return new SalaryData(entity);
    }

}
