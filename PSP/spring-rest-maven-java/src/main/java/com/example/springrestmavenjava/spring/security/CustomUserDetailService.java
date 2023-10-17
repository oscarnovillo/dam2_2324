package com.example.springrestmavenjava.spring.security;

import com.example.springrestmavenjava.domain.modelo.User;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class CustomUserDetailService implements UserDetailsService {
    private final Map<String, UserDetails> userMap = new HashMap<>();

    public CustomUserDetailService(PasswordEncoder bCryptPasswordEncoder) {
        userMap.put("user", createUser("user", bCryptPasswordEncoder.encode("user"), false, "USER"));
        userMap.put("admin", createUser("admin", bCryptPasswordEncoder.encode("admin"), true, "ROLE_ADsssMIN", "ADMIN","USER"));
    }

    @Override
    public UserDetails loadUserByUsername(final String username)  {
        return Optional.ofNullable(userMap.get(username))
                .orElseThrow(() -> new UsernameNotFoundException("User " + username + " does not exists"));
    }

    private SecurityUser createUser(String userName, String password, boolean withRestrictedPolicy, String... role) {
        return SecurityUser.builder().userName(userName)
                .password(password)
                .grantedAuthorityList(Arrays.stream(role)
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList()))
                .accessToRestrictedPolicy(withRestrictedPolicy).build();
    }
}
