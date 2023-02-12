package jp.fujino.SalaryDataManager.domain.service;

import jakarta.transaction.Transactional;
import jp.fujino.SalaryDataManager.domain.object.Money;
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

    /** Find by PrimaryKey **/
    public SalaryData findById(String month, String paymentType) {
        SalaryDataKey key = new SalaryDataKey(month, paymentType);
        Optional<SalaryDataEntity> entity = salaryDataRepository.findById(key);
        return new SalaryData(entity.get());
    }

    /** Find by Month **/
    public List<SalaryData> findByMonth(String month) {
        List<SalaryData> objects = new ArrayList<>();
        List<SalaryDataEntity> entities = salaryDataRepository.findByMonth(month);
        for (SalaryDataEntity entity : entities)
            objects.add(new SalaryData(entity));
        return objects;
    }

    /** Find by PaymentType **/
    public List<SalaryData> findByPaymentType(String paymentType) {
        List<SalaryData> objects = new ArrayList<>();
        List<SalaryDataEntity> entities = salaryDataRepository.findByPaymentType(paymentType);
        for (SalaryDataEntity entity : entities)
            objects.add(new SalaryData(entity));
        return objects;
    }

    /** Add Data **/
    public SalaryData addSalaryData(
            String createdBy,
            String month,
            String paymentType,
            Money money
    ) {
        Date timestamp = new Date();
        SalaryDataEntity entity = new SalaryDataEntity(
                timestamp,
                createdBy,
                timestamp,
                createdBy,
                0,
                month,
                paymentType,
                money.getAmount(),
                money.getCurrency().getCurrencyCode()
        );
        entity = salaryDataRepository.save(entity);
        return new SalaryData(entity);
    }

    /** Save Data **/
    public SalaryData saveSalaryData(SalaryData data, String updatedBy) {
        Date timestamp = new Date();
        SalaryDataEntity entity = new SalaryDataEntity(
                data.getCreatedAt(),
                data.getCreatedBy(),
                timestamp,
                updatedBy,
                data.getExclusiveFlag(),
                data.getMonth(),
                data.getPaymentType(),
                data.getMoney().getAmount(),
                data.getMoney().getCurrency().getCurrencyCode()
        );
        entity = salaryDataRepository.save(entity);
        return new SalaryData(entity);
    }

}
