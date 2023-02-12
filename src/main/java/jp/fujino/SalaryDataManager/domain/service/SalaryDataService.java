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
    public SalaryData findById(final String month, final String paymentType) {
        final SalaryDataKey key = new SalaryDataKey(month, paymentType);
        final Optional<SalaryDataEntity> entity = salaryDataRepository.findById(key);
        return new SalaryData(entity.get());
    }

    /** Find by Month **/
    public List<SalaryData> findByMonth(final String month) {
        List<SalaryData> objects = new ArrayList<>();
        final List<SalaryDataEntity> entities = salaryDataRepository.findByMonth(month);
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

    /** Add Data **/
    public SalaryData addSalaryData(
            final String createdBy,
            final String month,
            final String paymentType,
            final Money money
    ) {
        final Date timestamp = new Date();
        final SalaryDataEntity entity = salaryDataRepository.save(
                new SalaryDataEntity(
                    timestamp,
                    createdBy,
                    timestamp,
                    createdBy,
                    0,
                    month,
                    paymentType,
                    money.amount(),
                    money.currencyCode()
                )
        );
        return new SalaryData(entity);
    }

    /** Save Data **/
    public SalaryData saveSalaryData(
            final SalaryData data,
            final String updatedBy
    ) {
        final Date timestamp = new Date();
        final SalaryDataEntity entity = salaryDataRepository.save(
                new SalaryDataEntity(
                    data.getCreatedAt(),
                    data.getCreatedBy(),
                    timestamp,
                    updatedBy,
                    data.getExclusiveFlag(),
                    data.getMonth(),
                    data.getPaymentType(),
                    data.getMoney().amount(),
                    data.getMoney().currencyCode()
                )
        );
        return new SalaryData(entity);
    }

}
