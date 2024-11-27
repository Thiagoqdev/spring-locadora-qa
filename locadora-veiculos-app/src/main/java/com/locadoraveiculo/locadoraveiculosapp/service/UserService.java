package com.locadoraveiculo.locadoraveiculosapp.service;

import com.locadoraveiculo.locadoraveiculosapp.dto.RegisterDTO;
import com.locadoraveiculo.locadoraveiculosapp.model.UserEntity;
import com.locadoraveiculo.locadoraveiculosapp.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public UserEntity findByLogin(String login) {
        return userRepository.findByLogin(login).orElse(null);
    }

    public void saveUser(RegisterDTO registerDTO) {
        // Verifica se usuário já existe antes de salvar
        if (userRepository.findByLogin(registerDTO.login()).isPresent()) {
            throw new RuntimeException("Usuário já existe");
        }

        String passwordEncrypted = new BCryptPasswordEncoder().encode(registerDTO.password());
        UserEntity user = new UserEntity(registerDTO.login(), passwordEncrypted, registerDTO.userRole());
        userRepository.save(user);
    }

}