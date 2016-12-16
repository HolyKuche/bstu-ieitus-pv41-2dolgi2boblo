package ru.tdtb.business.service;

import org.springframework.security.access.prepost.PreAuthorize;
import ru.tdtb.business.dto.DebtDto;

public interface DebtService {
    @PreAuthorize("hasAnyRole('USER')")
    DebtDto get(Long id);

    @PreAuthorize("hasAnyRole('USER')")
    Long create(DebtDto user);

    @PreAuthorize("hasAnyRole('USER')")
    void update(DebtDto user);

    @PreAuthorize("hasAnyRole('USER')")
    void delete(Long id);
}
