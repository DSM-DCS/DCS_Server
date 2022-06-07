package com.dsm.dcs.service.user;

import com.dsm.dcs.dto.response.UserListResponse;
import com.dsm.dcs.entity.user.User;
import com.dsm.dcs.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserFacade userFacade;

    public UserListResponse getUser(Pageable page) {
        List<User> userList = userFacade.getUserList();
        return getUserList(userList, page);
    }

    public UserListResponse searchUser(String name, Pageable page) {
        List<User> userList = userFacade.getUserByName(name);
        return getUserList(userList, page);
    }

    private UserListResponse getUserList(List<User> userList, Pageable page) {

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
    
}
