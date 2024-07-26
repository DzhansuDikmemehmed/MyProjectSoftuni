package bg.softuni.myproject.service.impl;

import bg.softuni.myproject.model.entity.Appointment;
import bg.softuni.myproject.model.entity.enums.AppointmentStatus;
import bg.softuni.myproject.repo.AppointmentRepository;
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
    private final ModelMapper modelMapper;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, ModelMapper modelMapper) {
        this.appointmentRepository = appointmentRepository;
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
    public boolean registerForAppointment(Long appointmentId) {
        Appointment appointment =  appointmentRepository.findById(appointmentId).orElse(null);

        if (appointment != null && appointment.getStatus() == AppointmentStatus.ACTIVE && appointment.getRemainingSpots()>0){

            appointment.setRemainingSpots(appointment.getRemainingSpots()-1);
            appointmentRepository.save(appointment);
            return true;
        }
        return false;
    }

    @Override
    public boolean unregisterFromAppointment(Long appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId).orElse(null);

        if (appointment != null && appointment.getStatus() == AppointmentStatus.ACTIVE) {
            appointment.setRemainingSpots(appointment.getRemainingSpots() + 1);
            appointmentRepository.save(appointment);
            return true;
        }
        return false;
    }


}
