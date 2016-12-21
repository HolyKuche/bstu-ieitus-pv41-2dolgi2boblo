package ru.tdtb.business.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tdtb.business.dao.UserDao;
import ru.tdtb.business.domain.User;
import ru.tdtb.business.dto.UserDto;
import ru.tdtb.application.mapping.TdtbMapper;
import ru.tdtb.business.service.AbstractService;
import ru.tdtb.business.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl extends AbstractService implements UserService {
    @Autowired
    private UserDao dao;
    @Autowired
    private TdtbMapper mapper;

    @Override
    public UserDto get(Long id) {
        return mapper.map(dao.get(id), UserDto.class);
    }

    @Override
    public Long create(UserDto user) {
        return dao.save(mapper.map(user, User.class));
    }

    @Override
    public void update(UserDto user) {
        dao.update(mapper.map(user, User.class));
    }

    @Override
    public void delete(Long id) {
        dao.delete(id);
    }

    @Override
    public List<UserDto> getFriends(Long userId) {
        return mapper.map(dao.get(userId).getFriends(), UserDto.class);
    }
}
