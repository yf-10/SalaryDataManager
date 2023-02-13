package jp.fujino.SalaryDataManager.domain.repository;

import jp.fujino.SalaryDataManager.infrastructure.entity.SalaryEntity;
import jp.fujino.SalaryDataManager.infrastructure.key.SalaryKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SalaryRepository extends JpaRepository<SalaryEntity, SalaryKey> {

    /**
     * CRUD: Read
     */

    /** Find by PrimaryKey **/
    Optional<SalaryEntity> findById(SalaryKey key);

    /** Exists by PrimaryKey **/
    boolean existsById(SalaryKey key);

    /** Find by Month **/
    List<SalaryEntity> findByMonth(String month);

    /** Find by Month Between **/
    List<SalaryEntity> findByMonthBetween(String from, String to);

    /** Find by Month After **/
    List<SalaryEntity> findByMonthAfter(String from);

    /** Find by Month Before **/
    List<SalaryEntity> findByMonthBefore(String to);

    /** Find by PaymentType **/
    List<SalaryEntity> findByPaymentType(String paymentType);

    /** Find by PaymentType In **/
    List<SalaryEntity> findByPaymentTypeIn(List<String> paymentType);

    /**
     * CRUD: Create, Update
     */

    /** Save **/
    SalaryEntity save(SalaryEntity entity);

    /**
     * CRUD: Delete
     */

    /** Delete **/
    void delete(SalaryEntity entity);

}
