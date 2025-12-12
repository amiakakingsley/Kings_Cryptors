package com.lovedays.kings_cryptos.JpaRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lovedays.kings_cryptos.Model.Account;

public interface AccountRepo extends JpaRepository<Account, Long> {
    
}
