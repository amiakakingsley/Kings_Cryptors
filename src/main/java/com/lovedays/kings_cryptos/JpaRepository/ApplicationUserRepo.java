package com.lovedays.kings_cryptos.JpaRepository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lovedays.kings_cryptos.Model.ApplicationUser;
@Repository
public interface ApplicationUserRepo  extends JpaRepository<ApplicationUser, Long> {

  Optional<ApplicationUser> findByEmail(String email); 
    
} 
