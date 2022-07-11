package com.dsm.dcs.service.user;

import com.dsm.dcs.dto.request.PhoneNumberRequest;
import com.dsm.dcs.dto.response.UserListResponse;
import com.dsm.dcs.dto.response.UserResponse;
import com.dsm.dcs.entity.account.Account;
import com.dsm.dcs.entity.account.AccountRepository;
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
    private final AccountRepository accountRepository;

    public UserListResponse getUser(Pageable page) {
        userFacade.checkRoleAdmin();
        return  userFacade.getUserList(page);
    }

    public UserListResponse searchUser(String name, Pageable page) {
        userFacade.checkRoleAdmin();
        return userFacade.getUserByName(name, page);
    }

    public UserResponse getUser() {
        Account account = userFacade.getCurrentUser();
        return UserResponse.builder()
                .accountId(account.getAccountId())
                .name(account.getName())
                .phoneNumber(account.getPhoneNumber())
                .build();
    }

    public void changePhoneNumber(PhoneNumberRequest request) {
        userFacade.checkRoleUser();
        Account account = userFacade.getCurrentUser();
        accountRepository.save(account.updatePhoneNumber(request.getPhoneNumber()));
    }

}
