package br.com.login.controller.dtos;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginDTO {
    @ApiModelProperty(name = "login", value = "luiz_segundo")
    private String login;
    @ApiModelProperty(name = "password", value = "123456")
    private String password;
}
