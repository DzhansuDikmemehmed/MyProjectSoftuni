package bg.softuni.myproject.service;

import bg.softuni.myproject.service.dto.AddAppointmentDto;
import bg.softuni.myproject.service.dto.AllAppointmentsDto;
import bg.softuni.myproject.service.dto.DetailsAppointmentDto;

import java.util.List;

public interface AppointmentService {

    Long createAppointment(AddAppointmentDto addAppointmentDto);

    List<AllAppointmentsDto> getAllAppointments();

    DetailsAppointmentDto getAppointmentDetails(Long id);

}
