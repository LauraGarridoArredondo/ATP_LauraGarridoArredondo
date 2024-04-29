package org.example.atp.config.Security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service ("UserDetailsService")
@RequiredArgsConstructor
public class TenistasUserDetailsService implements UserDetailsService{
    private final UserEnitityService userEntityService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userEntityService.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException(username + " no encontrado"));
    }
}
