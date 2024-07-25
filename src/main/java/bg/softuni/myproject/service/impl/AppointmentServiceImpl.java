package bg.softuni.myproject.service.impl;

import bg.softuni.myproject.model.entity.Appointment;
import bg.softuni.myproject.repo.AppointmentRepository;
import bg.softuni.myproject.service.AppointmentService;
import bg.softuni.myproject.service.dto.AddAppointmentDto;
import bg.softuni.myproject.service.dto.AllAppointmentsDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
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
      return   appointmentRepository.save(mappedApp).getId();

    }

    @Override
    public List<AllAppointmentsDto> getAllAppointments() {

        return appointmentRepository.findAll()
                .stream()
                .map(appointment -> {
                    AllAppointmentsDto dto = new AllAppointmentsDto();
                    dto.setImageUrl(appointment.getImageUrl());
                    dto.setCoachName(appointment.getCoachName());
                    dto.setType(appointment.getType());
                    dto.setAppointmentDateTime(appointment.getAppointmentDateTime());
                    dto.setPrice(appointment.getPrice());
                    return dto;})
                .collect(Collectors.toList());

    }



}
