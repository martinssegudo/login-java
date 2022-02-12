package br.com.login.entities;

import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.time.LocalDateTime;


@Table(name = "TB_ROLE")
@Entity
@GenericGenerator(
        name = "SEQ_ROLE",
        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
        parameters = {
                @Parameter(name = "sequence_name", value = "SEQ_ROLE"),
                @Parameter(name = "initial_value", value = "1"),
                @Parameter(name = "increment_size", value = "1")
        }
)
@Data
@Builder
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ROLE")
    @Column(name = "ROLE_ID", nullable = false)
    private Long id;
    @Column(name = "DS_NOME")
    private String name;
    @Column(name = "DS_NOME_TECNICO")
    private String technicalName;
    @Column(name = "DT_INICIO")
    private LocalDateTime startDate;
    @Column(name = "DT_FIM")
    private LocalDateTime endDate;

}
