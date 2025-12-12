 package com.lovedays.kings_cryptos.UserDetails;

import java.util.Collection;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.lovedays.kings_cryptos.Model.ApplicationUser;

public class CustomUserDetails implements UserDetails{


    private final ApplicationUser applicationUser;

    

    public CustomUserDetails(ApplicationUser applicationUser) {
        this.applicationUser = applicationUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
       return applicationUser.getRoles().stream().map(role -> new SimpleGrantedAuthority("ROLE" + role.getName())).collect(Collectors.toSet());
    }

    @Override
    public String getPassword() {
       return applicationUser.getPassword();
    }

    @Override
    public String getUsername() {
        return applicationUser.getEmail();
    }
    
}
