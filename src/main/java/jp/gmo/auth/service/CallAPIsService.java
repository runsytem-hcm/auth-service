package jp.gmo.auth.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import jp.gmo.auth.dto.AccountDto;

public interface CallAPIsService {

	AccountDto getAccountInfo(String email) ;
}
