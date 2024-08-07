package bg.softuni.myproject.repo;

import bg.softuni.myproject.model.entity.UserAppointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAppointmentRepository extends JpaRepository<UserAppointment, Long> {
    boolean existsByAppointmentIdAndUserId(Long appointmentId, Long userId);
    UserAppointment findByAppointmentIdAndUserId(Long appointmentId, Long userId);


    void deleteByAppointmentId(long appointmentId);
}
