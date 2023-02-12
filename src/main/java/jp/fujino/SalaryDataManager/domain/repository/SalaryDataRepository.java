package jp.fujino.SalaryDataManager.domain.repository;

import jp.fujino.SalaryDataManager.infrastructure.entity.SalaryDataEntity;
import jp.fujino.SalaryDataManager.infrastructure.key.SalaryDataKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SalaryDataRepository extends JpaRepository<SalaryDataEntity, SalaryDataKey> {

    /** Find by PrimaryKey **/
    Optional<SalaryDataEntity> findById(SalaryDataKey key);

    /** Find by Month **/
    List<SalaryDataEntity> findByMonth(String month);

    /** Find by PaymentType **/
    List<SalaryDataEntity> findByPaymentType(String paymentType);

    /** Add Data **/
    SalaryDataEntity save(SalaryDataEntity entity);

}
