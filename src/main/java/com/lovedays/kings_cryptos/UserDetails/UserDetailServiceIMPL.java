package com.lovedays.kings_cryptos.UserDetails;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lovedays.kings_cryptos.JpaRepository.ApplicationUserRepo;

@Service
public class UserDetailServiceIMPL implements UserDetailsService {
    private final ApplicationUserRepo applicationUserRepo;

    public UserDetailServiceIMPL(ApplicationUserRepo applicationUserRepo) {
        this.applicationUserRepo = applicationUserRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return applicationUserRepo.findByEmail(username).map(CustomUserDetails::new).orElseThrow(() -> new UsernameNotFoundException("user not found"));
    }

    
}
