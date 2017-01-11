package ru.tdtb.business.service.impl;

import org.apache.commons.codec.binary.Hex;
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
    public UserDto getCurrent() {
        return mapper.map(getCurrentUser(), UserDto.class);
    }

    @Override
    public Long create(UserDto user) {
        user.setHashPass(encryptUserPassword(user.getHashPass()));
        return dao.save(mapper.map(user, User.class));
    }

    @Override
    public void update(UserDto user) {
        User current = getCurrentUser();
        if (current.getId().equals(user.getId())) {
            user.setLogin(current.getLogin());
            user.setHashPass(current.getHashPass());
            User mappedUser = mapper.map(user, User.class);
            mappedUser.setFriends(current.getFriends());
            dao.update(mappedUser);
        }
    }

    @Override
    public void delete(Long id) {
        if (getCurrentUser().getId().equals(id)) {
            dao.delete(id);
        }
    }

    @Override
    public List<UserDto> getFriendsByCurrentUser() {
        return mapper.map(getCurrentUser().getFriends(), UserDto.class);
    }

    @Override
    public void addFriendByCurrentUser(UserDto user) {
        getCurrentUser().getFriends().add(mapper.map(user, User.class));
    }

    @Override
    public void deleteFriendByCurrentUser(Long userId) {
        User user = getCurrentUser().getFriends().stream()
                .filter(u -> u.getId() == userId)
                .findFirst()
                .get();
        getCurrentUser().getFriends().remove(user);
    }

    @Override
    public List<UserDto> getBySearchString(String searchString) {
        return mapper.map(dao.getBySearchString(searchString), UserDto.class);
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
