package com.dsm.dcs.service.user;

import com.dsm.dcs.dto.request.FindPasswordRequest;
import com.dsm.dcs.entity.user.UserRepository;
import com.dsm.dcs.exception.UserNotFoundException;
import com.dsm.dcs.facade.UserFacade;
import com.dsm.dcs.infra.ses.SesUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class FindPasswordService {

    private final UserRepository userRepository;
    private final SesUtil sesUtil;
    private final UserFacade userFacade;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void execute(FindPasswordRequest request) {

        String email = request.getEmail();

        String result = changePassword();

        userRepository.findByEmail(email)
                .filter(users -> userFacade.checkVerified(
                        userFacade.isVerified(email)
                ))
                .map(users -> users.updatePassword(passwordEncoder.encode(result)))
                .map(userRepository::save)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        sesUtil.SendEmailForPassword(email, result);
    }

    public static String changePassword() {
        StringBuilder result = new StringBuilder();
        int idx = 0;

        char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
                'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

        for (int i = 0; i < 10; i++) {
            idx = (int) (charSet.length * Math.random());
            result.append(charSet[idx]);
        }

        return result.toString();
    }
}
