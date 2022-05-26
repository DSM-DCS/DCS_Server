package com.dsm.dcs.service.user;

import com.dsm.dcs.dto.response.UserListResponse;
import com.dsm.dcs.entity.user.User;
import com.dsm.dcs.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserFacade userFacade;

    public UserListResponse getUser() {

        List<User> userList = userFacade.getUsetList();
        List<UserListResponse.UserResponse> userResponses = new ArrayList<>();

        for(User user : userList) {
            userResponses.add(
                    UserListResponse.UserResponse.builder()
                            .name(user.getName())
                            .studentNumber(user.getStudentNumber())
                            .build()
            );
        }

        return new UserListResponse(userResponses);

    }

    public UserListResponse.UserResponse searchUser(String name) {
        User user = userFacade.getUserByName(name);

        return UserListResponse.UserResponse.builder()
                .name(user.getName())
                .studentNumber(user.getStudentNumber())
                .build();
    }


}