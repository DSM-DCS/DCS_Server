package com.dsm.dcs.service.user;

import com.dsm.dcs.dto.response.UserListResponse;
import com.dsm.dcs.dto.response.UserResponse;
import com.dsm.dcs.entity.user.User;
import com.dsm.dcs.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserFacade userFacade;

    public UserListResponse getUser(Pageable page) {
        return  userFacade.getUserList(page);
    }

    public UserListResponse searchUser(String name, Pageable page) {
        return userFacade.getUserByName(name, page);
    }

    public UserResponse getUser() {
        User user = userFacade.getCurrentUser();
        return UserResponse.builder()
                .accountId(user.getAccountId())
                .name(user.getName())
                .studentNumber(user.getStudentNumber())
                .phoneNumber(user.getPhoneNumber())
                .email(user.getEmail())
                .build();
    }

}
