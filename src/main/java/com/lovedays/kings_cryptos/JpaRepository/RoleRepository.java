package com.lovedays.kings_cryptos.JpaRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lovedays.kings_cryptos.Model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String roleName);
}
