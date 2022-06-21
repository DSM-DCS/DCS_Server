package com.dsm.dcs.facade;

import com.dsm.dcs.entity.admin.Admin;
import com.dsm.dcs.entity.admin.AdminRepository;
import com.dsm.dcs.exception.AdminNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminFacade {

    private final AdminRepository adminRepository;

    public Admin findByAdminId(String adminId) {
        return adminRepository.findByAdminId(adminId)
                .orElseThrow(() -> AdminNotFoundException.EXCEPTION);
    }

}
