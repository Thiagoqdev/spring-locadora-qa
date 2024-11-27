package com.locadoraveiculo.locadoraveiculosapp.repository;

import com.locadoraveiculo.locadoraveiculosapp.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByLogin(String login);
}