
package bg.softuni.myproject.service.impl;

import bg.softuni.myproject.model.entity.Appointment;
import bg.softuni.myproject.model.entity.User;
import bg.softuni.myproject.model.entity.UserAppointment;
import bg.softuni.myproject.model.entity.enums.AppointmentStatus;
import bg.softuni.myproject.repo.AppointmentRepository;
import bg.softuni.myproject.repo.UserAppointmentRepository;
import bg.softuni.myproject.repo.UserRepository;
import bg.softuni.myproject.service.AppointmentService;
import bg.softuni.myproject.service.dto.AddAppointmentDto;
import bg.softuni.myproject.service.dto.AllAppointmentsDto;
import bg.softuni.myproject.service.dto.DetailsAppointmentDto;
import org.hibernate.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final UserAppointmentRepository userAppointmentRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, UserAppointmentRepository userAppointmentRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.appointmentRepository = appointmentRepository;
        this.userAppointmentRepository = userAppointmentRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Long createAppointment(AddAppointmentDto addAppointmentDto) {

        Appointment mappedApp = modelMapper.map(addAppointmentDto, Appointment.class);
        mappedApp.setRemainingSpots(addAppointmentDto.getMaxParticipants());


        return   appointmentRepository.save(mappedApp).getId();

    }

    @Override
    public List<AllAppointmentsDto> getAllAppointments() {

        return appointmentRepository.findAll()
                .stream()
                .map(appointment -> {
                    AllAppointmentsDto dto = new AllAppointmentsDto();
                    dto.setId(appointment.getId());
                    dto.setImageUrl(appointment.getImageUrl());
                    dto.setCoachName(appointment.getCoachName());
                    dto.setType(appointment.getType());
                    dto.setAppointmentDateTime(appointment.getAppointmentDateTime());
                    dto.setPrice(appointment.getPrice());
                    return dto;})
                .collect(Collectors.toList());

    }

    @Override
    public DetailsAppointmentDto getAppointmentDetails(Long id) {
        Optional<Appointment> appointmentOptional = appointmentRepository.findById(id);
        if (appointmentOptional.isEmpty()) {
            throw new ObjectNotFoundException("Appointment not found!", id);
        }

        DetailsAppointmentDto dto = modelMapper.map(appointmentOptional.get(), DetailsAppointmentDto.class);
        dto.setRemainingSpots(appointmentOptional.get().getRemainingSpots());
        return dto;    }

    @Override
    public boolean registerForAppointment(Long appointmentId, Long userId) {
        Appointment appointment = appointmentRepository.findById(appointmentId).orElse(null);
        User user = userRepository.findById(userId).orElse(null);

        if (appointment != null && user != null && appointment.getStatus() == AppointmentStatus.ACTIVE && appointment.getRemainingSpots() > 0) {
            // Проверка дали потребителят вече е регистриран
            if (userAppointmentRepository.existsByAppointmentIdAndUserId(appointmentId, userId)) {
                return false; // Потребителят вече е регистриран
            }

            appointment.setRemainingSpots(appointment.getRemainingSpots() - 1);
            appointmentRepository.save(appointment);

            UserAppointment userAppointment = new UserAppointment();
            userAppointment.setAppointment(appointment);
            userAppointment.setUser(user);
            userAppointmentRepository.save(userAppointment);
            return true;
        }
        return false;
    }

    @Override
    public boolean unregisterFromAppointment(Long appointmentId, Long userId) {
        Appointment appointment = appointmentRepository.findById(appointmentId).orElse(null);
        User user = userRepository.findById(userId).orElse(null);

        if (appointment != null && user != null && appointment.getStatus() == AppointmentStatus.ACTIVE) {
            UserAppointment userAppointment = userAppointmentRepository.findByAppointmentIdAndUserId(appointmentId, userId);
            if (userAppointment != null) {
                appointment.setRemainingSpots(appointment.getRemainingSpots() + 1);
                appointmentRepository.save(appointment);

                userAppointmentRepository.delete(userAppointment);
                return true;
            }
        }
        return false;
    }

    @Override
    public void deleteAppointment(long appointmentId) {
        appointmentRepository.deleteById(appointmentId);
    }



    @Override
    public boolean isUserRegistered(Long appointmentId, Long userId) {
        return userAppointmentRepository.existsByAppointmentIdAndUserId(appointmentId, userId);
    }

}
