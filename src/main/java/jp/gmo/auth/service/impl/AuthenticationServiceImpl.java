package jp.gmo.auth.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import jp.gmo.auth.dto.AccountDto;
import jp.gmo.auth.exception.CustomUsernameNotFoundException;
import jp.gmo.auth.request.LoginRequest;
import jp.gmo.auth.security.JwtTokenProvider;
import jp.gmo.auth.service.AuthenticationService;
import jp.gmo.auth.service.CallAPIsService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

	private static final Logger LOG = LoggerFactory.getLogger(AuthenticationServiceImpl.class);
	private AuthenticationManager authenticationManager;
	private final CallAPIsService callAPIsService;
	private final JwtTokenProvider tokenProvider;
	
	@Override
	public String executeLogin(LoginRequest request) {
		
		try {
			
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
			authenticationManager.authenticate(authentication);
			
			AccountDto response = callAPIsService.getAccountInfo(request.getEmail());
			response.setPassword("");
			return tokenProvider.doGenerateToken(response, false);

		} catch (AuthenticationException e) {
			LOG.error("{}", e);
			throw new CustomUsernameNotFoundException();
		}
	}
}
