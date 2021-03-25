package jp.gmo.auth.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import jp.gmo.auth.config.UrlApiProperties;
import jp.gmo.auth.dto.AccountDto;
import jp.gmo.auth.request.LoginRequest;
import jp.gmo.auth.service.CallAPIsService;
import jp.gmo.auth.utils.Utils;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class CallAPIsServiceImpl implements CallAPIsService {

    private static final Logger LOG = LoggerFactory.getLogger(CallAPIsServiceImpl.class);
    private final RestTemplate template;
    private final UrlApiProperties urlApiProperties;

    @Override
    public AccountDto getAccountInfo(String email) {

        AccountDto accountDto = new AccountDto();

            String url = urlApiProperties.getGetAccountInfo();
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                    .queryParam("email", email);
        ResponseEntity<String> response = null;
            try {
                response = template.exchange(builder.toUriString(), HttpMethod.GET, null, String.class);
            } catch (ResourceAccessException e) {
                e.printStackTrace();
            }


            if (response.getBody() != null) {
                // Parsing entity of response
                try {
                    accountDto = Utils.convertJsonStringToObject(response.getBody(), AccountDto.class);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }

        return accountDto;
    }

}
