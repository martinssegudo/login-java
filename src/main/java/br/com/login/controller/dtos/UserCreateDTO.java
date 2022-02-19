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
public class UserCreateDTO {
    @ApiModelProperty(name = "nome", value = "Luiz Segundo")
    private String name;
    @ApiModelProperty(name = "Login", value = "Luiz_Segundo")
    private String login;
    @ApiModelProperty(name = "Senha", value = "123547483")
    private String passsword;
    @ApiModelProperty(name = "Data Nascimento", value = "27/08/1982")
    private String birthDay;
}
