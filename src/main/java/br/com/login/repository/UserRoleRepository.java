package br.com.login.repository;

import br.com.login.entities.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRole,Long> {

    @Query(value = "select ur from " +
            " UserRole ur " +
            " where ur.user.login like :login " +
            "           and ur.endData is not null ")
    public List<UserRole> findByLogin(@Param("login") String login);
}
