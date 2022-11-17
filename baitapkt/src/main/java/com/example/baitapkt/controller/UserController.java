package com.example.baitapkt.controller;

import com.example.baitapkt.mode.User;
import com.example.baitapkt.service.UserService;
import com.example.baitapkt.userDto.UserDto;
import com.example.baitapkt.userDto.UserDtoPass;
import com.example.baitapkt.userDto.UserDtoPassNew;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    public UserDto addUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @GetMapping
    public Page<UserDto> findPaginated(@RequestParam("page") Optional<Integer> page,
                                    @RequestParam("limit") Optional<Integer> limit) {
        int currentPage = page.orElse(1);
        int pageSize = limit.orElse(10);
        return userService.findPaginated(PageRequest.of(currentPage - 1, pageSize));
    }

    @GetMapping("{id}")
    public UserDto findUserById(@PathVariable int id) {
        return userService.getUserById(id);
    }

    @GetMapping("/search")
    public List<UserDto> findUserByName(@RequestParam(name = "name") String name) {
        return userService.getUserByName(name);
    }

    @PutMapping("{id}")
    public UserDto updateUser(@PathVariable int id,@RequestBody User user) {
        return userService.updateUser(id,user);
    }

    @DeleteMapping("{id}")
    public String deleteUser(@PathVariable int id) {
        return userService.deleteUser(id);
    }

    @PutMapping("{id}/update-avatar")
    public void updateAvata(@PathVariable int id,@RequestBody User user) {
         userService.updateAvata(id,user);
    }

    @PutMapping("{id}/update-password")
    public void updatePassword(@PathVariable int id,@RequestBody UserDtoPass user) {
        userService.updatePassword(id,user);
    }

    @PostMapping("{id}/fotgot-password")
    public UserDtoPassNew fotgotpassword(@PathVariable int id,@RequestBody UserDtoPassNew user) {
       return userService.fotgotPassword(id,user);
    }

    @GetMapping("/getAll")
    public List<User> findAllProducts() {
        return userService.getUser();
    }


}
