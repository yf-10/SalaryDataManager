package jp.fujino.SalaryDataManager.domain.repository;

import jp.fujino.SalaryDataManager.infrastructure.entity.SalaryDataEntity;
import jp.fujino.SalaryDataManager.infrastructure.key.SalaryDataKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SalaryDataRepository extends JpaRepository<SalaryDataEntity, SalaryDataKey> {

    /**
     * CRUD: Read
     */

    /** Find by PrimaryKey **/
    Optional<SalaryDataEntity> findById(SalaryDataKey key);

    /** Exists by PrimaryKey **/
    boolean existsById(SalaryDataKey key);

    /** Find by Month **/
    List<SalaryDataEntity> findByMonth(String month);

    /** Find by Month Between **/
    List<SalaryDataEntity> findByMonthBetween(String from, String to);

    /** Find by Month After **/
    List<SalaryDataEntity> findByMonthAfter(String from);

    /** Find by Month Before **/
    List<SalaryDataEntity> findByMonthBefore(String to);

    /** Find by PaymentType **/
    List<SalaryDataEntity> findByPaymentType(String paymentType);

    /** Find by PaymentType In **/
    List<SalaryDataEntity> findByPaymentTypeIn(List<String> paymentType);

    /**
     * CRUD: Create, Update
     */

    /** Save **/
    SalaryDataEntity save(SalaryDataEntity entity);

    /**
     * CRUD: Delete
     */

    /** Delete **/
    void delete(SalaryDataEntity entity);

}
