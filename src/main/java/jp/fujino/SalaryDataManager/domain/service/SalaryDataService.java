package jp.fujino.SalaryDataManager.domain.service;

import jakarta.transaction.Transactional;
import jp.fujino.SalaryDataManager.domain.object.SalaryData;
import jp.fujino.SalaryDataManager.domain.repository.SalaryDataRepository;
import jp.fujino.SalaryDataManager.infrastructure.entity.SalaryDataEntity;
import jp.fujino.SalaryDataManager.infrastructure.key.SalaryDataKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class SalaryDataService {

    @Autowired
    SalaryDataRepository salaryDataRepository;

    /** Find by Primary key **/
    public SalaryData findById(String month, String paymentType) {
        SalaryDataKey key = new SalaryDataKey(month, paymentType);
        SalaryDataEntity entity = salaryDataRepository.findById(key);
        return new SalaryData(entity);
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

}
