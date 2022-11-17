package com.example.baitapkt.service;

import com.example.baitapkt.mode.User;
import com.example.baitapkt.repository.UserRepository;
import com.example.baitapkt.userDto.UserDto;
import com.example.baitapkt.userDto.UserDtoPass;
import com.example.baitapkt.userDto.UserDtoPassNew;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public UserDto getUserById(int id) {
        return userRepository.findById(id);
    }

    public UserDto saveUser(User user) {
        return userRepository.save(user);
    }

    public String deleteUser(int id) {
        userRepository.delete(id);
        return "user removed !! " + id;
    }

    public UserDto updateUser(Integer id, User user) {
        return userRepository.update(id,user);
    }

    public UserDto updateAvata(Integer id, User user) {
        return userRepository.updateAvata(id,user);
    }
    public List<User> getUser() {
        return userRepository.getAllUser();
    }

    public List<UserDto> getUserByName(String name) {
        return userRepository.search(name);
    }

    public void updatePassword(Integer id, UserDtoPass user) {
        userRepository.updatePassword(id,user);
    }

    public UserDtoPassNew fotgotPassword(Integer id, UserDtoPassNew userDtoPassNew) {
        return userRepository.fotgotPassword(id,userDtoPassNew);
    }

    public Page<UserDto> findPaginated(Pageable pageable) {
        return userRepository.findPaginated(pageable);
    }
}
