package com.dsm.dcs.facade;

import com.dsm.dcs.entity.admin.Admin;
import com.dsm.dcs.entity.admin.AdminRepository;
import com.dsm.dcs.entity.user.User;
import com.dsm.dcs.exception.AdminNotFoundException;
import com.dsm.dcs.exception.ForbiddenException;
import com.dsm.dcs.exception.InvalidJwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminFacade {

    private final AdminRepository adminRepository;

    public Admin getCurrentAdmin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw InvalidJwtException.EXCEPTION;
        }

        return findByAdminId(authentication.getName());
    }

    public Admin findByAdminId(String adminId) {
        return adminRepository.findByAdminId(adminId)
                .orElseThrow(() -> AdminNotFoundException.EXCEPTION);
    }

    public Boolean getRoleTeacherBoolean() {
        if(getCurrentAdmin().getRole().name() != "ROLE_TEACHER"){
            return false;
        }
        return true;
    }

    public Boolean getRoleCourierBoolean() {
        if(getCurrentAdmin().getRole().name() != "ROLE_COURIER"){
            return false;
        }
        return true;
    }


    public void getRoleTeacher() {
        if(!getRoleTeacherBoolean()){
            throw ForbiddenException.EXCEPTION;
        }
    }

    public Boolean getRoleCourier() {
        if(!getRoleCourierBoolean()){
            throw ForbiddenException.EXCEPTION;
        }
        return true;
    }


}
