package com.lovedays.kings_cryptos.Services;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lovedays.kings_cryptos.Dtos.ApplicationUserDto;
import com.lovedays.kings_cryptos.Enumarization.TransactionType;
import com.lovedays.kings_cryptos.JpaRepository.AccountRepo;
import com.lovedays.kings_cryptos.JpaRepository.ApplicationUserRepo;
import com.lovedays.kings_cryptos.JpaRepository.RoleRepository;
import com.lovedays.kings_cryptos.JpaRepository.TransactionRepo;
import com.lovedays.kings_cryptos.Model.Account;
import com.lovedays.kings_cryptos.Model.ApplicationUser;
import com.lovedays.kings_cryptos.Model.Role;
import com.lovedays.kings_cryptos.Model.Transaction;
@Service
public class ApplicationUserServiceIMPL implements ApplicationUserService {

    
    private final ApplicationUserRepo applicationUserRepo;
    private final AccountRepo accountRepo;
    private final TransactionRepo transactionRepo;

    private final PasswordEncoder passwordEncoder;

    private final RoleRepository roleRepository;

    public ApplicationUserServiceIMPL(ApplicationUserRepo applicationUserRepo, AccountRepo accountRepo,
            TransactionRepo transactionRepo, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.applicationUserRepo = applicationUserRepo;
        this.roleRepository = roleRepository;
        this.accountRepo = accountRepo;
        this.transactionRepo = transactionRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public ApplicationUserDto RegisterUser(ApplicationUserDto applicationUserDto) {
        
        ApplicationUser newUser = new ApplicationUser();
        newUser.setUserName(applicationUserDto.getUserName());
        newUser.setEmail(applicationUserDto.getEmail());
        newUser.setPassword(passwordEncoder.encode(applicationUserDto.getPassword()));

        String roleName = "USER"; 
		
	    Role existingRole = roleRepository.findByName(roleName);
	    
	    if (existingRole == null) {
	        Role newRole = new Role();
	        newRole.setName(roleName);
	        existingRole = roleRepository.save(newRole); // Save the new role
	    }

		Set<Role> roles =  new HashSet<>();
		roles.add(existingRole); 
	    
	    newUser.setRoles(roles);

        newUser = applicationUserRepo.save(newUser);

        
        Account account = new Account();
        account.setUser(newUser);
        account.setBalance(0.0);
        account.setUserName(newUser.getUserName());
        
        account = accountRepo.save(account);
        

       
        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setCreatedAt(LocalDateTime.now());
        transaction.setAmount(0.0);
        transaction.setType(TransactionType.OPENING_ACCOUNT);

        transactionRepo.save(transaction);              // <-- TRANSACTION SAVED HERE

        account.getTransactions().add(transaction);
        account = accountRepo.save(account);
 
        applicationUserDto.setId(newUser.getId());
       
        return applicationUserDto;
    }

    @Override
    public Optional<ApplicationUser> findUserByEmail(String email) {
     return applicationUserRepo.findByEmail(email);
    }

    
    
}
