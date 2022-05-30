package com.dsm.dcs.entity.user;

import com.dsm.dcs.entity.Authority;
import com.dsm.dcs.entity.AuthorityScope;
import com.dsm.dcs.entity.SortStandard;
import com.dsm.dcs.entity.user.vo.QUserDetailsVO;
import com.dsm.dcs.entity.user.vo.QUserListInfoVO;
import com.dsm.dcs.entity.user.vo.UserDetailsVO;
import com.dsm.dcs.entity.user.vo.UserListInfoVO;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

import static com.dsm.dcs.entity.user.QUser.user;

@RequiredArgsConstructor
public class UserRepositoryCustomImpl implements UserRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<UserListInfoVO> searchUser(AuthorityScope scope, SortStandard sort,
                                           User currentUser, String name) {

        return queryFactory
                .select(new QUserListInfoVO(
                        user.id,
                        user.name,
                        user.studentNumber,
                        user.authority.eq(Authority.TEACHER)
                ))
                .from(user)
                .where(
                        buildFilteringCondition(scope)
                )
                .orderBy(buildSortCondition(sort))
                .fetch();
    }

    @Override
    public UserDetailsVO queryUserDetails(Long userId, LocalDate startAt, LocalDate endAt) {
        return queryFactory
                .select(new QUserDetailsVO(
                        user.id,
                        user.name,
                        user.studentNumber
                ))
                .from(user)
                .fetchOne();
    }

    private BooleanExpression buildFilteringCondition(AuthorityScope scope) {
        switch (scope) {
            case ALL:
                return user.authority.eq(Authority.TEACHER)
                        .or(user.authority.eq(Authority.USER));
            case STUDENT:
                return user.authority.eq(Authority.USER);
            case TEACHER:
                return user.authority.eq(Authority.TEACHER);
            default:
                return null;
        }
    }

    private OrderSpecifier[] buildSortCondition(SortStandard sort) {
        if (sort == SortStandard.NAME) return new OrderSpecifier[]{
                user.name.asc()
        };
        return new OrderSpecifier[]{
                user.studentNumber.asc()
        };
    }
}
