package jp.gmo.auth.request;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import jp.gmo.auth.constants.MessageConstants;
import jp.gmo.auth.constants.RegexConstants;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginRequest {

    @NotNull(message = "{" + MessageConstants.CONST_MSG_VALIDATE_NOT_NULL + "}")
    @Pattern(message = "{" + MessageConstants.CONST_MSG_VALIDATE_EMAIL + "}", regexp = RegexConstants.EMAIL_REGEX)
    private String email;

    @NotNull(message = "{" + MessageConstants.CONST_MSG_VALIDATE_NOT_NULL + "}")
    @Pattern(message = "{" + MessageConstants.CONST_MSG_VALIDATE_PASSWORD + "}", regexp = RegexConstants.PASSWORD_REGEX)
    private String password;
    
    private Boolean rememberMe;
}
