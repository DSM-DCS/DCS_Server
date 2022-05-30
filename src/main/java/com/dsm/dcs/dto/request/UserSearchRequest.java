package com.dsm.dcs.dto.request;

import com.dsm.dcs.entity.AuthorityScope;
import com.dsm.dcs.entity.SortStandard;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class UserSearchRequest {

    @NotNull
    private final AuthorityScope scope;

    @NotNull
    private final SortStandard sort;

    private final String name;

}
