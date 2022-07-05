package com.dsm.dcs.service.user;

import com.dsm.dcs.dto.request.UpdatePasswordRequest;
import com.dsm.dcs.entity.user.UserRepository;
import com.dsm.dcs.exception.UserNotFoundException;
import com.dsm.dcs.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UpdatePasswordService {

    private final UserRepository userRepository;
    private final UserFacade userFacade;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void execute(UpdatePasswordRequest request) {
        userFacade.getRole();
        String email = request.getEmail();

        userRepository.findByEmail(email)
                .filter(user -> userFacade.checkVerified(
                        userFacade.isVerified(email)
                ))
                .map(user -> user.updatePassword(passwordEncoder.encode(request.getNewPassword())))
                .map(userRepository::save)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }
}
