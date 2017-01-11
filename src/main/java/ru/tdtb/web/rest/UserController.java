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

    @RequestMapping(value = "/current", method = RequestMethod.GET)
    public UserDto getCurrent() {
        return service.getCurrent();
    }

    @RequestMapping(method = RequestMethod.POST)
    public void create(@RequestBody UserDto user) {
        service.create(user);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void update(@RequestBody UserDto user) {
        service.update(user);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @RequestMapping(value = "/friends", method = RequestMethod.GET)
    public List<UserDto> getFriendsByCurrentUser() {
        return service.getFriendsByCurrentUser();
    }

    @RequestMapping(value = "/friends", method = RequestMethod.POST)
    public void addFriendByCurrentUser(@RequestBody UserDto user) {
        service.addFriendByCurrentUser(user);
    }

    @RequestMapping(value = "/friends/{userId}", method = RequestMethod.DELETE)
    public void deleteFriendByCurrentUser(@PathVariable Long userId) {
        service.deleteFriendByCurrentUser(userId);
    }

    @RequestMapping(value = "/search/{searchString}", method = RequestMethod.GET)
    public List<UserDto> getBySearchString(@PathVariable String searchString) {
        return service.getBySearchString(searchString);
    }
}
