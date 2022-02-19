package br.com.login.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Table(name = "TB_USER")
@Entity
@GenericGenerator(
        name = "SEQ_USER",
        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
        parameters = {
                @Parameter(name = "sequence_name", value = "SEQ_USER"),
                @Parameter(name = "initial_value", value = "1"),
                @Parameter(name = "increment_size", value = "1")
        }
)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_USER")
    @Column(name = "USER_ID", nullable = false)
    private Long id;

    @Column(name = "DS_NOME")
    private String name;
    @Column(name = "DS_LOGIN", unique = true)
    private String login;
    @Column(name = "DS_SENHA")
    private String password;
    @Column(name = "DT_NASCIMENTO")
    private LocalDate birthDay;
    @Column(name = "DT_CRIACAO")
    private LocalDateTime createDate;
}