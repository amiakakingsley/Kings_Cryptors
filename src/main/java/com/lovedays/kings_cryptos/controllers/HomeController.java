package com.lovedays.kings_cryptos.controllers;


import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lovedays.kings_cryptos.Dtos.ApplicationUserDto;
import com.lovedays.kings_cryptos.Model.ApplicationUser;
import com.lovedays.kings_cryptos.Services.ApplicationUserService;
import com.lovedays.kings_cryptos.Services.CryptoServices;

@Controller
public class HomeController {

    private final CryptoServices cryptoServices;
    private final ApplicationUserService applicationUserService;
    public HomeController(CryptoServices cryptoServices, ApplicationUserService applicationUserService) {
        this.cryptoServices  = cryptoServices;
        this.applicationUserService = applicationUserService;
    
    }

    @GetMapping("/home")
    public String showDashboard(Model model) {
        model.addAttribute("botName", "KingsCryptos");
        model.addAttribute("description", "Your personal crypto signal and market tracking assistant.");
        return "home";
    }

@GetMapping("/api/user/me")
public ResponseEntity<?> getLoggedInUser(@AuthenticationPrincipal UserDetails userDetails) {

    // Find user from DB using email (username)
    ApplicationUser appUser = applicationUserService
            .findUserByEmail(userDetails.getUsername())
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));

    // Convert to DTO
    ApplicationUserDto dto = new ApplicationUserDto(
            appUser.getId(),
            appUser.getUserName(),
            appUser.getEmail(),
            null, 
            appUser.getRoles()
    );

    return ResponseEntity.ok(dto);
}



    @GetMapping("/coins")
    @ResponseBody
    public List<Map<String, Object>> getCoins() {
        return cryptoServices.getMarketData(); 
    }

}