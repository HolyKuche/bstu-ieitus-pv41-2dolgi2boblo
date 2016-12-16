package ru.tdtb.business.service.security.desicions;

import ru.tdtb.application.security.CustomUserDetails;
import ru.tdtb.business.domain.User;

public abstract class AbstractAccessDecisionManager implements DomainAccessDecisionManager {

    private Class<?> clazz;

    public AbstractAccessDecisionManager(Class<?> clazz) {
        this.clazz = clazz;
    }

    @Override
    public boolean supports(Object o) {
        if (o == null) {
            throw new IllegalArgumentException();
        }
        return clazz.isAssignableFrom(o.getClass());
    }

    protected boolean areEquals(User user, CustomUserDetails userDetails) {
        if (user == null || userDetails == null) return false;
        return user.getLogin().equals(userDetails.getUsername());
    }
}
