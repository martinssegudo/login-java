package br.com.login.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name = "TB_ACTIVE_USER_INFO")
@Entity
@GenericGenerator(
        name = "SEQ_ACTIVE_USER_INFO",
        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
        parameters = {
                @Parameter(name = "sequence_name", value = "SEQ_ACTIVE_USER_INFO"),
                @Parameter(name = "initial_value", value = "1"),
                @Parameter(name = "increment_size", value = "1")
        }
)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ActiveUserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ACTIVE_USER_INFO")
    @Column(name = "USER_ID", nullable = false)
    private Long id;
    @OneToOne
    private User user;
    @Column(name = "DT_INICIO")
    private LocalDateTime startDate;
    @Column(name = "DT_FIM")
    private LocalDateTime endDate;
}
