package jp.gmo.auth.service;

import jp.gmo.auth.request.LoginRequest;

public interface AuthenticationService {

	String executeLogin (LoginRequest request);
}
