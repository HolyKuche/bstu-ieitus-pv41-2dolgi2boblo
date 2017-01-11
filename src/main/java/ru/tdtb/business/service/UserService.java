package ru.tdtb.business.service;

import org.springframework.security.access.prepost.PreAuthorize;
import ru.tdtb.business.domain.User;
import ru.tdtb.business.dto.UserDto;

import java.util.List;

public interface UserService {
    @PreAuthorize("hasAnyRole('USER')")
    UserDto get(Long id);

    @PreAuthorize("hasAnyRole('USER')")
    UserDto getCurrent();

    @PreAuthorize("hasAnyRole('USER')")
    Long create(UserDto user);

    @PreAuthorize("hasAnyRole('USER')")
    void update(UserDto user);

    @PreAuthorize("hasAnyRole('USER')")
    void delete(Long id);

    @PreAuthorize("hasAnyRole('USER')")
    List<UserDto> getFriendsByCurrentUser();

    @PreAuthorize("hasAnyRole('USER')")
    void addFriendByCurrentUser(UserDto user);

    @PreAuthorize("hasAnyRole('USER')")
    void deleteFriendByCurrentUser(Long userId);

    @PreAuthorize("hasAnyRole('USER')")
    List<UserDto> getBySearchString(String searchString);
}
