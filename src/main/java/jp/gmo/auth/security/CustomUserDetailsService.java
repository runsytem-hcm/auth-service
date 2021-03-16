package jp.gmo.auth.security;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import jp.gmo.auth.dto.AccountDto;
import jp.gmo.auth.exception.CustomUsernameNotFoundException;
import jp.gmo.auth.service.CallAPIsService;
import lombok.AllArgsConstructor;

@Service("userDetailsService")
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
	
    private final CallAPIsService callAPIsService;

    @Override
    public UserDetails loadUserByUsername(final String email) {
        AccountDto data = callAPIsService.getAccountInfo(email);
        if (data != null) {
            return createSpringSecurityUser(email, data);
        } else {
            throw new CustomUsernameNotFoundException();
        }
    }

    private org.springframework.security.core.userdetails.User createSpringSecurityUser(String email,
        AccountDto account) {
        return new org.springframework.security.core.userdetails.User(email, account.getPassword(), new ArrayList<>());
    }
}
