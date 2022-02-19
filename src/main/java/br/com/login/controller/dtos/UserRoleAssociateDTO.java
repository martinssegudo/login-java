package br.com.login.controller.dtos;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleAssociateDTO {
    @ApiModelProperty(name = "login", value = "luiz_segundo")
    private String login;
    @ApiModelProperty(name = "perfil", value = "admin")
    private String role;
}
