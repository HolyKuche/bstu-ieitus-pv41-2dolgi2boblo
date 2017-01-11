package ru.tdtb.business.dao;

import ru.tdtb.business.domain.Debt;

import java.util.List;

public interface DebtDao extends AbstractDao<Debt, Long> {
    List<Debt> getAllByWhoId(Long userId);
    List<Debt> getAllByWhomId(Long userId);
}
