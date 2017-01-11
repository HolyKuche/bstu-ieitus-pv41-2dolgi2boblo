package ru.tdtb.business.service;

import org.springframework.security.access.prepost.PreAuthorize;
import ru.tdtb.business.dto.DebtDto;

import java.util.List;

public interface DebtService {
    @PreAuthorize("hasAnyRole('USER')")
    DebtDto get(Long id);

    @PreAuthorize("hasAnyRole('USER')")
    List<DebtDto> getAllOutgoingsForCurrentUser();

    @PreAuthorize("hasAnyRole('USER')")
    List<DebtDto> getAllIncomingsForCurrentUser();

    @PreAuthorize("hasAnyRole('USER')")
    Long create(DebtDto debt);

    @PreAuthorize("hasAnyRole('USER')")
    void update(DebtDto debt);

    @PreAuthorize("hasAnyRole('USER')")
    void delete(Long id);
}
