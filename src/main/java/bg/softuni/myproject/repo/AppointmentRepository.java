package bg.softuni.myproject.repo;

import bg.softuni.myproject.model.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}
