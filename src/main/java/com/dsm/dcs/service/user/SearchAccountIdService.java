package com.dsm.dcs.service.user;

import com.dsm.dcs.dto.response.UserAccountIdResponse;
import com.dsm.dcs.entity.user.User;
import com.dsm.dcs.entity.user.UserRepository;
import com.dsm.dcs.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SearchAccountIdService {

    private final UserRepository userRepository;

    public UserAccountIdResponse execute(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        return new UserAccountIdResponse(user.getAccountId());
    }
}
