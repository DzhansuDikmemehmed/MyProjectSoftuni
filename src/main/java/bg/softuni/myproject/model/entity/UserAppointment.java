package bg.softuni.myproject.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "user_appointments")
public class UserAppointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Appointment appointment;

    public UserAppointment() {
    }

    public Long getId() {
        return id;
    }

    public UserAppointment setId(Long id) {
        this.id = id;
        return this;
    }

    public User getUser() {
        return user;
    }

    public UserAppointment setUser(User user) {
        this.user = user;
        return this;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public UserAppointment setAppointment(Appointment appointment) {
        this.appointment = appointment;
        return this;
    }
}
