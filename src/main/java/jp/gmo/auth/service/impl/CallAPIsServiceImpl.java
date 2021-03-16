package jp.gmo.auth.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import jp.gmo.auth.config.UrlApiProperties;
import jp.gmo.auth.dto.AccountDto;
import jp.gmo.auth.request.LoginRequest;
import jp.gmo.auth.service.CallAPIsService;
import jp.gmo.auth.utils.Utils;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CallAPIsServiceImpl implements CallAPIsService {

	private static final Logger LOG = LoggerFactory.getLogger(CallAPIsServiceImpl.class);
	private final RestTemplate template;
	private final UrlApiProperties urlApiProperties;

	@Override
	public AccountDto getAccountInfo(String email) {

		AccountDto accountDto = new AccountDto();
		try {
			String url = urlApiProperties.getGetAccountInfo();
			
			LoginRequest request = new LoginRequest();
			request.setEmail(email);
			
			HttpEntity<LoginRequest> entity = new HttpEntity<>(request);
			ResponseEntity<String> response = template.postForEntity(url, entity, String.class);

			if (response.getBody() != null) {
				// Parsing entity of response
				accountDto = Utils.convertJsonStringToObject(response.getBody(), AccountDto.class);
			}
		} catch (Exception e) {
			LOG.error("{}", e);
		}
		return accountDto;
	}

}
