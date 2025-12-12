package com.lovedays.kings_cryptos.JpaRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lovedays.kings_cryptos.Model.Transaction;

public interface TransactionRepo extends JpaRepository<Transaction, Long> {
    
}
