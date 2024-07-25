package bg.softuni.myproject.service;

import bg.softuni.myproject.service.dto.AddAppointmentDto;

public interface AppointmentService {

    Long createAppointment(AddAppointmentDto addAppointmentDto);
}
