package jp.gmo.auth.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jp.gmo.auth.constants.MessageConstants;
import jp.gmo.auth.request.LoginRequest;
import jp.gmo.auth.response.ResponseCommon;
import jp.gmo.auth.service.AuthenticationService;
import jp.gmo.auth.utils.ResponseUtils;
import jp.gmo.auth.utils.Utils;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    
    @PostMapping("/authenticate")
    public ResponseEntity<ResponseCommon> executeLogin(@Valid @RequestBody LoginRequest request){
        return new ResponseEntity<>(ResponseUtils.success(authenticationService.executeLogin(request), Utils.getMessage(MessageConstants.CONST_MSG_NORMAL)), HttpStatus.OK);
    }
}
