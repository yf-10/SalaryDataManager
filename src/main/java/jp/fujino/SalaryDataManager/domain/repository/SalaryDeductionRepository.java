package jp.fujino.SalaryDataManager.domain.repository;

import jp.fujino.SalaryDataManager.infrastructure.entity.SalaryDeductionEntity;
import jp.fujino.SalaryDataManager.infrastructure.key.SalaryDeductionKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SalaryDeductionRepository extends JpaRepository<SalaryDeductionEntity, SalaryDeductionKey> {

    /**
     * CRUD: Read
     */

    /** Find by PrimaryKey **/
    Optional<SalaryDeductionEntity> findById(SalaryDeductionKey key);

    /** Exists by PrimaryKey **/
    boolean existsById(SalaryDeductionKey key);

    /** Find by Month **/
    List<SalaryDeductionEntity> findByMonth(String month);

    /** Find by Month Between **/
    List<SalaryDeductionEntity> findByMonthBetween(String from, String to);

    /** Find by Month After **/
    List<SalaryDeductionEntity> findByMonthAfter(String from);

    /** Find by Month Before **/
    List<SalaryDeductionEntity> findByMonthBefore(String to);

    /** Find by PaymentType **/
    List<SalaryDeductionEntity> findByDeductionType(String deductionType);

    /** Find by PaymentType In **/
    List<SalaryDeductionEntity> findByDeductionTypeIn(List<String> deductionType);

    /**
     * CRUD: Create, Update
     */

    /** Save **/
    SalaryDeductionEntity save(SalaryDeductionEntity entity);

    /**
     * CRUD: Delete
     */

    /** Delete **/
    void delete(SalaryDeductionEntity entity);

}
