package br.com.login.repository;

import br.com.login.entities.ActiveUserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserActiveInfoRepository extends JpaRepository<ActiveUserInfo, Long> {

    @Query("SELECT aui " +
            " FROM ActiveUserInfo aui " +
            "  WHERE aui.user.login LIKE :login " +
            "       AND aui.endDate is null ")
    public ActiveUserInfo findByLogin(@Param("login") String login);
}
