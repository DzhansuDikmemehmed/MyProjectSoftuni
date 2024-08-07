package bg.softuni.myproject.service;

import bg.softuni.myproject.model.entity.Appointment;
import bg.softuni.myproject.model.entity.enums.AppointmentStatus;
import bg.softuni.myproject.repo.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentStatusUpdaterService {
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Scheduled(cron = "0 0 * * * *") // Всеки час
//@Scheduled(cron = "0 * * * * *")
    public void updateAppointmentsStatus() {
        // Извличане на всички активни тренировки
        List<Appointment> appointments = appointmentRepository.findAll();

        for (Appointment appointment : appointments) {
            if (appointment.getRemainingSpots() == 0 &&
                    appointment.getStatus() == AppointmentStatus.ACTIVE) {
                // Актуализиране на статуса на тренировката на COMPLETED
                appointment.setStatus(AppointmentStatus.COMPLETED);
                appointmentRepository.save(appointment);
                System.out.println("Актуализиране на статус на тренировка с ID " + appointment.getId() + " на COMPLETED.");
            }
        }
    }
}
