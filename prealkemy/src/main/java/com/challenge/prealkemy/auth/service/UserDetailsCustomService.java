package com.challenge.prealkemy.auth.service;

import com.challenge.prealkemy.auth.dto.UserDTO;
import com.challenge.prealkemy.auth.entity.UserEntity;
import com.challenge.prealkemy.auth.repository.UserRepository;
import com.challenge.prealkemy.exception.RegisterException;
import com.challenge.prealkemy.exceptionsMensaje.ExceptionMensaje;
import com.challenge.prealkemy.service.EmailService;
import java.io.IOException;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author river
 */
@Service

public class UserDetailsCustomService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmailService emailService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        UserEntity userEntity = userRepository.findByUsername(userName);
        if (userEntity == null) {
            throw new RegisterException(ExceptionMensaje.USUARIO_Y_CONTRASEÑA_NO_VALIDOS);
        }
        return new User(userEntity.getUsername(), userEntity.getPassword(), Collections.emptyList());

    }

    public void register(UserDTO userDTO) throws IOException {

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userDTO.getUsername());
        userEntity.setPassword(userDTO.getPassword());

        if (userRepository.findByUsername(userEntity.getUsername()) == null) {

            userEntity = userRepository.save(userEntity);
            emailService.sendWelcomeEmailTo(userEntity.getUsername());

        } else {
            throw new RegisterException(ExceptionMensaje.USUARIO_YA_EXISTE);

        }

    }
}
