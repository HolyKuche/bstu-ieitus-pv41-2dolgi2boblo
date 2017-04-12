package ru.tdtb.business.dao.impl;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import ru.tdtb.business.dao.DebtDao;
import ru.tdtb.business.domain.Debt;

import java.util.List;

@Repository
@SuppressWarnings("unchecked")
public class DebtDaoImpl extends AbstractDaoImpl<Debt, Long> implements DebtDao {
    public DebtDaoImpl() {
        super(Debt.class);
    }

    @Override
    public List<Debt> getAllByWhoId(Long userId, Integer pageNumber, Integer pageSize) {
        return getCurrentSession()
                .createCriteria(Debt.class)
                .add(Restrictions.eq("who.id", userId))
                .addOrder(Order.desc("id"))
                .setFirstResult((pageNumber - 1) * pageSize)
                .setMaxResults(pageSize)
                .setCacheable(false)
                .list();
    }

    @Override
    public List<Debt> getAllByWhomId(Long userId) {
        return getCurrentSession()
                .createCriteria(Debt.class)
                .add(Restrictions.eq("whom.id", userId))
                .setCacheable(false)
                .list();
    }

    @Override
    public Integer getCountByWhoId(Long userId) {
        return getCurrentSession()
                .createCriteria(Debt.class)
                .add(Restrictions.eq("who.id", userId))
                .list()
                .size();
    }
}
