package bg.softuni.myproject.repo;

import bg.softuni.myproject.model.entity.OnlineTrainingSession;
import bg.softuni.myproject.model.entity.enums.TrainingType;
import bg.softuni.myproject.service.dto.OnlineTrainingByCategoryDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OnlineTrainingRepository extends JpaRepository<OnlineTrainingSession, Long> {

//    @Query("SELECT s FROM OnlineTrainingSession s JOIN s.categories c WHERE c.name = :categoryName")
//    List<OnlineTrainingSession> findAllByCategoryName(@Param("categoryName") String categoryName);

//    List<OnlineTrainingSession> findAllByCategories_Name(TrainingType category);

//    List<OnlineTrainingSession> findAllByCategory_Name(TrainingType category);

    @Query("SELECT new bg.softuni.myproject.service.dto.OnlineTrainingByCategoryDto(ot.id, ot.title, ot.description, ot.imageUrl) FROM OnlineTrainingSession ot WHERE ot.type = :type")
    List<OnlineTrainingByCategoryDto> findByType(@Param("type") TrainingType type);}