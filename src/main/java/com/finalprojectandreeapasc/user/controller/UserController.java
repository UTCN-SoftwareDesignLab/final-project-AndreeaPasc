package com.finalprojectandreeapasc.user.controller;
import com.finalprojectandreeapasc.user.dto.UserListDTO;
import com.finalprojectandreeapasc.user.dto.UserMinimalDTO;
import com.finalprojectandreeapasc.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.finalprojectandreeapasc.UrlMapping.ENTITY;
import static com.finalprojectandreeapasc.UrlMapping.USERS;

@RestController
@RequestMapping(USERS)
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<UserListDTO> allUsers() {
        return userService.allUsersForList();
    }

    @PostMapping
    public UserMinimalDTO create(@RequestBody UserMinimalDTO user){
        return userService.create(user);
    }

    @PutMapping(ENTITY)
    public UserMinimalDTO update(@PathVariable Long id, @RequestBody UserMinimalDTO user) {
        return userService.update(id, user);
    }

    @DeleteMapping(ENTITY)
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }

    @GetMapping(ENTITY)
    public UserMinimalDTO getRecipe(@PathVariable Long id) {
        return userService.get(id);
    }
}
