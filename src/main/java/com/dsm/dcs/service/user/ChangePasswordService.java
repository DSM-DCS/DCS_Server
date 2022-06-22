package com.dsm.dcs.service.user;

import com.dsm.dcs.dto.request.ChangePasswordRequest;
import com.dsm.dcs.entity.user.UserRepository;
import com.dsm.dcs.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ChangePasswordService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void execute(ChangePasswordRequest request) {

        String accountId = request.getAccountId();

        userRepository.findByAccountId(accountId)
                .map(user -> user.updatePassword(passwordEncoder.encode(request.getNewPassword())))
                .map(userRepository::save)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }
}
