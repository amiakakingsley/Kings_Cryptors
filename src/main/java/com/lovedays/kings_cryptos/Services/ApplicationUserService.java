package com.lovedays.kings_cryptos.Services;

import java.util.Optional;

import org.junit.platform.engine.reporting.ReportEntry;

import com.lovedays.kings_cryptos.Dtos.ApplicationUserDto;
import com.lovedays.kings_cryptos.Model.ApplicationUser;

public interface ApplicationUserService {
    ApplicationUserDto RegisterUser(ApplicationUserDto applicationUserDto);

    Optional<ApplicationUser> findUserByEmail(String email);
}
