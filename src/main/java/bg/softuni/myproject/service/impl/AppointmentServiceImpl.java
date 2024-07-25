package bg.softuni.myproject.service.impl;

import bg.softuni.myproject.model.entity.Appointment;
import bg.softuni.myproject.repo.AppointmentRepository;
import bg.softuni.myproject.service.AppointmentService;
import bg.softuni.myproject.service.dto.AddAppointmentDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

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
}
