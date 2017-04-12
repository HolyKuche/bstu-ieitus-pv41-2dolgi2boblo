package ru.tdtb.business.service;

import org.springframework.security.access.prepost.PreAuthorize;
import ru.tdtb.business.dto.DebtDto;

import java.io.IOException;
import java.util.List;

public interface DebtService {
    @PreAuthorize("hasAnyRole('USER')")
    DebtDto get(Long id);

    @PreAuthorize("hasAnyRole('USER')")
    List<DebtDto> getAllOutgoingsForCurrentUser(Integer pageNumber, Integer pageSize);

    @PreAuthorize("hasAnyRole('USER')")
    List<DebtDto> getAllIncomingsForCurrentUser();

    @PreAuthorize("hasAnyRole('USER')")
    Integer getCountOfAllOutgoingsForCurrentUser();

    @PreAuthorize("hasAnyRole('USER')")
    Long create(DebtDto debt);

    @PreAuthorize("hasAnyRole('USER')")
    void update(DebtDto debt);

    @PreAuthorize("hasAnyRole('USER')")
    void addImage(Long debtId, byte[] bytes) throws IOException;

    @PreAuthorize("hasAnyRole('USER')")
    byte[] getImage(Long debtId) throws IOException;

    @PreAuthorize("hasAnyRole('USER')")
    void delete(Long id);
}
