package ru.tdtb.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.tdtb.business.dto.UserDto;
import ru.tdtb.business.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService service;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public UserDto get(@PathVariable Long id) {
        return service.get(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Long create(@RequestBody UserDto user) {
        return service.create(user);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void update(@RequestBody UserDto user) {
        service.update(user);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @RequestMapping(value = "/{userId}/friends", method = RequestMethod.GET)
    public List<UserDto> getFriends(@PathVariable Long userId) {
        return service.getFriends(userId);
    }
}
