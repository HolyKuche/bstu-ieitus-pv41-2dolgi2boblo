package ru.tdtb.business.dao.impl;

import org.springframework.stereotype.Repository;
import ru.tdtb.business.dao.DebtDao;
import ru.tdtb.business.domain.Debt;

@Repository
public class DebtDaoImpl extends AbstractDaoImpl<Debt, Long> implements DebtDao{
    public DebtDaoImpl() {
        super(Debt.class);
    }
}
