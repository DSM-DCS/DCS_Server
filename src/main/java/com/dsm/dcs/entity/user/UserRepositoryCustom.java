package com.dsm.dcs.entity.user;

import com.dsm.dcs.entity.AuthorityScope;
import com.dsm.dcs.entity.SortStandard;
import com.dsm.dcs.entity.user.vo.UserDetailsVO;
import com.dsm.dcs.entity.user.vo.UserListInfoVO;

import java.time.LocalDate;
import java.util.List;

public interface UserRepositoryCustom {

    List<UserListInfoVO> searchUser(AuthorityScope scope, SortStandard sort, User currentUser, String name);

    UserDetailsVO queryUserDetails(Long userId, LocalDate startAt, LocalDate endAt);
}
