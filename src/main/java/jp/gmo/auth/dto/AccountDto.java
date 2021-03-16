package jp.gmo.auth.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountDto {
    private String employeeCode;
    private String email;
    private String employeeName;
    private String password;
    private Integer roleId;
}
