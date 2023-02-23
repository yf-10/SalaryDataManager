package jp.fujino.SalaryDataManager.domain.repository;

import jp.fujino.SalaryDataManager.infrastructure.entity.SalaryEntity;
import jp.fujino.SalaryDataManager.infrastructure.key.SalaryKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SalaryRepository extends JpaRepository<SalaryEntity, SalaryKey> {

    /** Find by PrimaryKey **/
    Optional<SalaryEntity> findById(SalaryKey key);

    /** Exists by PrimaryKey **/
    boolean existsById(SalaryKey key);

    /** Find by Month **/
    List<SalaryEntity> findByMonth(String month);

    /** Find by Month Between **/
    List<SalaryEntity> findByMonthBetween(String monthFrom, String monthTo);

    /** Find by Month After **/
    List<SalaryEntity> findByMonthAfter(String monthFrom);

    /** Find by Month Before **/
    List<SalaryEntity> findByMonthBefore(String monthTo);

    /** Find by PaymentItem **/
    List<SalaryEntity> findByDeductionAndPaymentItem(Boolean deduction, String paymentItem);

    /** Find by PaymentItem In **/
    List<SalaryEntity> findByDeductionAndPaymentItemIn(Boolean deduction, List<String> paymentItemList);

    /** Save **/
    SalaryEntity save(SalaryEntity entity);

    /** Delete **/
    void delete(SalaryEntity entity);

}
