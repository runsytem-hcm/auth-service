package jp.gmo.auth.service;

import jp.gmo.auth.dto.AccountDto;

public interface CallAPIsService {

	AccountDto getAccountInfo(String email);
}
