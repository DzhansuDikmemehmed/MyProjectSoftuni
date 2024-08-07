package bg.softuni.myproject.repo;

import bg.softuni.myproject.model.entity.Role;
import bg.softuni.myproject.model.entity.User;
import bg.softuni.myproject.model.entity.enums.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRolesRepository extends JpaRepository<Role,Long> {
}
