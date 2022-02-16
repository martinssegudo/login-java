package br.com.login.repository;

import br.com.login.entities.ActiveUserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserActiveInfoRepository extends JpaRepository<ActiveUserInfo, Long> {

    @Query("Select aui " +
            " from ActiveUserInfo aui " +
            " where aui.user.login like :login " +
            "       aui.endDate is null ")
    public ActiveUserInfo findByLogin(@Param("login") String login);
}
