package br.com.login.entities;

import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.time.LocalDateTime;


@Table(name = "TB_USER_ROLE")
@Entity
@GenericGenerator(
        name = "SEQ_USER_ROLE",
        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
        parameters = {
                @Parameter(name = "sequence_name", value = "SEQ_USER_ROLE"),
                @Parameter(name = "initial_value", value = "1"),
                @Parameter(name = "increment_size", value = "1")
        }
)
@Data
@Builder
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_USER_ROLE")
    @Column(name = "USER_ROLE_ID", nullable = false)
    private Long id;

    @OneToOne
    private User user;
    @OneToOne
    private Role role;
    @Column(name = "DT_INICIO")
    private LocalDateTime startData;
    @Column(name = "DT_FIM")
    private LocalDateTime endData;
}
