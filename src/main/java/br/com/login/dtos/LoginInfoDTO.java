package br.com.login.dtos;

import br.com.login.entities.Role;
import br.com.login.entities.User;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class LoginInfoDTO {
    private User user;
    private List<Role> roles;
}
