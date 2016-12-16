package ru.tdtb.business.service.security.desicions;

import ru.tdtb.application.security.CustomUserDetails;
import ru.tdtb.application.security.PermissionType;

public interface DomainAccessDecisionManager {
    boolean supports(Object o);

    boolean hasPermission(Object o, CustomUserDetails userDetails, PermissionType type);
}

