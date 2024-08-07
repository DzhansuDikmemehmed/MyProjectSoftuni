package bg.softuni.myproject.model.entity;

import bg.softuni.myproject.model.entity.enums.UserRoles;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Column(unique = true)
    @Enumerated(EnumType.STRING)
    private UserRoles role;

    public Role() {
    }

    public long getId() {
        return id;
    }

    public Role setId(long id) {
        this.id = id;
        return this;
    }

    public UserRoles getRole() {
        return role;
    }

    public Role setRole(UserRoles name) {
        this.role = name;
        return this;
    }
}
