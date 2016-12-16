package ru.tdtb.business.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import ru.tdtb.business.dao.DebtDao;
import ru.tdtb.business.domain.Debt;
import ru.tdtb.business.dto.DebtDto;
import ru.tdtb.application.mapping.TdtbMapper;
import ru.tdtb.business.service.AbstractService;
import ru.tdtb.business.service.DebtService;

public class DebtServiceImpl extends AbstractService implements DebtService {
    @Autowired
    private DebtDao dao;
    @Autowired
    private TdtbMapper mapper;

    @Override
    public DebtDto get(Long id) {
        return mapper.map(dao.get(id), DebtDto.class);
    }

    @Override
    public Long create(DebtDto user) {
        return dao.save(mapper.map(user, Debt.class));
    }

    @Override
    public void update(DebtDto user) {
        dao.update(mapper.map(user, Debt.class));
    }

    @Override
    public void delete(Long id) {
        dao.delete(id);
    }
}
