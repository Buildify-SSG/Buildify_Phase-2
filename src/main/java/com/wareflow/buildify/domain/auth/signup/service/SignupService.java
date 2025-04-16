package com.wareflow.buildify.domain.auth.signup.service;

import com.wareflow.buildify.dto.UserDTO;

public interface SignupService {
    boolean register(UserDTO userDTO);
}
