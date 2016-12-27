package ru.tdtb.business.service.impl;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tdtb.business.dao.UserDao;
import ru.tdtb.business.domain.User;
import ru.tdtb.business.dto.UserDto;
import ru.tdtb.application.mapping.TdtbMapper;
import ru.tdtb.business.service.AbstractService;
import ru.tdtb.business.service.UserService;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.util.List;

@Service
@Transactional
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
        user.setHashPass(encryptUserPassword(user.getHashPass()));
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

    private String encryptUserPassword(String password) {
        if (password.length() <= 15) {
            try {
                Security.addProvider(new BouncyCastleProvider());
                MessageDigest messageDigest = MessageDigest.getInstance("SHA-256", "BC");
                byte[] digest = messageDigest.digest(password.getBytes());
                return new String(Hex.encodeHex(digest));
            } catch (NoSuchAlgorithmException | NoSuchProviderException e) {
                e.printStackTrace();
            }
        }
        return "";
    }
}
