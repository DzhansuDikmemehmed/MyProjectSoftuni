package bg.softuni.myproject.repo;

import bg.softuni.myproject.model.entity.ExRateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExRateRepository extends JpaRepository<ExRateEntity, Long> {

    Optional<ExRateEntity> findByCurrency(String currency);
}
