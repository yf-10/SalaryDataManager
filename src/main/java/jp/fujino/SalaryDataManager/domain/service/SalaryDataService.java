package jp.fujino.SalaryDataManager.domain.service;

import jakarta.transaction.Transactional;
import jp.fujino.SalaryDataManager.domain.object.SalaryData;
import jp.fujino.SalaryDataManager.domain.repository.SalaryDataRepository;
import jp.fujino.SalaryDataManager.infrastructure.entity.SalaryDataEntity;
import jp.fujino.SalaryDataManager.infrastructure.key.SalaryDataKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SalaryDataService {

    @Autowired
    SalaryDataRepository salaryDataRepository;

    /** Find by Primary key **/
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
            int amount
    ) {
        Date timestamp = new Date();
        SalaryDataEntity entity = new SalaryDataEntity();
        entity.setCreatedAt(timestamp);
        entity.setCreatedBy(createdBy);
        entity.setUpdatedAt(timestamp);
        entity.setUpdatedBy(createdBy);
        entity.setMonth(month);
        entity.setPaymentType(paymentType);
        entity.setAmount(amount);
        entity = salaryDataRepository.save(entity);
        return new SalaryData(entity);
    }

    /** Save Data **/
    public SalaryData saveSalaryData(SalaryData data) {
        Date timestamp = new Date();
        SalaryDataEntity entity = new SalaryDataEntity();
        entity.setCreatedAt(data.getCreatedAt());
        entity.setCreatedBy(data.getCreatedBy());
        entity.setUpdatedAt(timestamp);
        entity.setUpdatedBy(data.getUpdatedBy());
        entity.setMonth(data.getMonth());
        entity.setPaymentType(data.getPaymentType());
        entity.setAmount(data.getAmount());
        entity = salaryDataRepository.save(entity);
        return new SalaryData(entity);
    }

}
