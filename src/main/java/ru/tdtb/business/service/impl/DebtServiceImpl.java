package ru.tdtb.business.service.impl;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tdtb.business.dao.DebtDao;
import ru.tdtb.business.domain.Debt;
import ru.tdtb.business.domain.User;
import ru.tdtb.business.dto.DebtDto;
import ru.tdtb.application.mapping.TdtbMapper;
import ru.tdtb.business.dto.UserDto;
import ru.tdtb.business.service.AbstractService;
import ru.tdtb.business.service.DebtService;

import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
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
    public List<DebtDto> getAllOutgoingsForCurrentUser(Integer pageNumber, Integer pageSize) {
        User currentUser = getCurrentUser();
        return dao.getAllByWhoId(currentUser.getId(), pageNumber, pageSize)
                .stream().map(debt -> {
                    DebtDto dto = mapper.map(debt, DebtDto.class);
                    if (debt.getImagePath() != null) {
                        dto.setHaveImage(true);
                    }
                    return dto;
                }).collect(Collectors.toList());
    }

    @Override
    public List<DebtDto> getAllIncomingsForCurrentUser() {
        User currentUser = getCurrentUser();
        return mapper.map(dao.getAllByWhomId(currentUser.getId())
                .stream().sorted(Comparator.comparing(Debt::getInitDateTime).reversed())
                .unordered().collect(Collectors.toList()), DebtDto.class);
    }

    @Override
    public Integer getCountOfAllOutgoingsForCurrentUser() {
        User currentUser = getCurrentUser();
        return dao.getCountByWhoId(currentUser.getId());
    }

    @Override
    public Long create(DebtDto debt) {
        User currentUser = getCurrentUser();
        debt.setInitDateTime(new Date());
        debt.setWho(mapper.map(currentUser, UserDto.class));
        return dao.save(mapper.map(debt, Debt.class));
    }

    @Override
    public void update(DebtDto debt) {
        dao.update(mapper.map(debt, Debt.class));
    }

    @Override
    public void addImage(Long debtId, byte[] bytes) throws IOException {
        String path = "/home/kuche/MEGA/Labs/2dolgi2boblo/images/" + debtId + ".jpg";
        FileUtils.writeByteArrayToFile(new File(path), bytes);
        dao.get(debtId).setImagePath(path);
    }

    @Override
    public byte[] getImage(Long debtId) throws IOException {
        String path = "/home/kuche/MEGA/Labs/2dolgi2boblo/images/" + debtId + ".jpg";
        return FileUtils.readFileToByteArray(new File(path));
    }

    @Override
    public void delete(Long id) {
        Debt debt = dao.get(id);
        User currentUser = getCurrentUser();
        if (debt.getWhom().getId().equals(currentUser.getId())) {
            dao.delete(id);
        }
    }
}
