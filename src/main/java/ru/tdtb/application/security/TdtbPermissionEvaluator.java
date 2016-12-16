package ru.tdtb.application.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import ru.tdtb.business.service.security.desicions.DomainAccessDecisionManager;
import org.apache.commons.lang3.NotImplementedException;

import java.io.Serializable;
import java.util.List;

public class TdtbPermissionEvaluator implements PermissionEvaluator {
    @Autowired
    private List<DomainAccessDecisionManager> managers;

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        for (DomainAccessDecisionManager manager : managers) {
            if (!manager.supports(targetDomainObject)) continue;
            return manager.hasPermission(
                    targetDomainObject,
                    (CustomUserDetails) authentication.getPrincipal(),
                    PermissionType.getByName((String) permission)
            );
        }

        throw new ManagersNotFoundException(targetDomainObject.getClass().toString());
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        throw new NotImplementedException("Authentication by identity not supported yet!");
    }
}
