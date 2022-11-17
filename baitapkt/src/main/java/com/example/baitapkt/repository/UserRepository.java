package com.example.baitapkt.repository;


import com.example.baitapkt.exception.ErrorMessagePass;
import com.example.baitapkt.mode.User;
import com.example.baitapkt.userDto.UserDto;
import com.example.baitapkt.userDto.UserDtoPass;
import com.example.baitapkt.userDto.UserDtoPassNew;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Repository
public class UserRepository {

    public static List<User> listFake = new ArrayList<>(List.of
            (new User(1, "dung1", "dung@gmail.com", "099247534", "hanam", "link", "123"),
                    new User(2, "dung2", "dung@gmail.com", "099247534", "hanam", "linh", "123"),
                    new User(3, "dung3", "dung@gmail.com", "099247534", "hanam", "linh", "123"),
                    new User(4, "dung4", "dung@gmail.com", "099247534", "hanam", "linh", "123"),
                    new User(5, "dung5", "dung@gmail.com", "099247534", "hanam", "linh", "123"),
                    new User(6, "dung6", "dung@gmail.com", "099247534", "hanam", "linh", "123")));

    public List<User> getAllUser() {
        return listFake;
    }


    public List<UserDto> search(String name) {
        List<UserDto> userDtoList = new ArrayList<>();
        List<User> userList = listFake.stream().filter(x -> x.getName().startsWith(name)).collect(Collectors.toList());
        for (int i = 0; i < userList.size(); i++) {
            UserDto userDto = new UserDto();
            userDto.setId(userList.get(i).getId());
            userDto.setName(userList.get(i).getName());
            userDto.setAddress(userList.get(i).getAddress());
            userDto.setNumber(userList.get(i).getNumber());
            userDto.setEmail(userList.get(i).getEmail());
            userDto.setAvatar(userList.get(i).getAvatar());
            userDtoList.add(userDto);
        }
        return userDtoList;
    }

    public UserDto findById(int id) {
        for (int i = 0; i < listFake.size(); i++) {
            if (listFake.get(i).getId() == (id)) {
                UserDto userDto = new UserDto();
                userDto.setId(id);
                userDto.setName(listFake.get(i).getName());
                userDto.setAddress(listFake.get(i).getAddress());
                userDto.setNumber(listFake.get(i).getNumber());
                userDto.setEmail(listFake.get(i).getEmail());
                userDto.setAvatar(listFake.get(i).getAvatar());
                return userDto;
            }
        }
        return null;
    }

    public UserDto save(User u) {
        User user = new User();
        int id = listFake.get(listFake.size() - 1).getId() + 1;
        user.setId(id);
        user.setName(u.getName());
        user.setAddress(u.getAddress());
        user.setNumber(u.getNumber());
        user.setEmail(u.getEmail());
        user.setPassword(u.getPassword());
        listFake.add(user);
        UserDto userDto = new UserDto();
        userDto.setId(id);
        userDto.setName(u.getName());
        userDto.setAddress(u.getAddress());
        userDto.setNumber(u.getNumber());
        userDto.setEmail(u.getEmail());
        userDto.setAvatar(u.getAvatar());
        return userDto;
    }

    public UserDto update(Integer id1, User user) {
        int idx = -1;
        int id = 0;
        for (int i = 0; i < listFake.size(); i++) {
            if (listFake.get(i).getId() == (id1)) {
                id = id1;
                idx = i;
                break;
            }
        }
        if (idx == -1) {
            throw new ErrorMessagePass(HttpStatus.BAD_REQUEST, "không tìm thấy id " + id1);
        }
        User user2 = listFake.get(idx);
        User user1 = new User();
        user1.setId(id);
        user1.setName(user.getName());
        user1.setNumber(user.getNumber());
        user1.setEmail(user2.getEmail());
        user1.setAddress(user.getAddress());
        user1.setAvatar(user2.getAvatar());
        listFake.set(idx, user1);
        UserDto userDto = new UserDto();
        userDto.setId(id);
        userDto.setName(user.getName());
        userDto.setAddress(user.getAddress());
        userDto.setNumber(user.getNumber());
        userDto.setEmail(user2.getEmail());
        userDto.setAvatar(user2.getAvatar());
        return userDto;
    }


    public String delete(Integer id) {
        listFake.removeIf(x -> x.getId() == (id));
        return null;
    }


    public UserDto updateAvata(Integer id1, User user) {
        int idx = -1;
        int id = 0;
        for (int i = 0; i < listFake.size(); i++) {
            if (listFake.get(i).getId() == (id1)) {
                id = id1;
                idx = i;
                break;
            }
        }
        if (idx == -1) {
            throw new ErrorMessagePass(HttpStatus.BAD_REQUEST, "không tìm thấy id " + id1);
        }
        User user2 = listFake.get(idx);

        User user1 = new User();
        user1.setId(id);
        user1.setName(user2.getName());
        user1.setNumber(user2.getNumber());
        user1.setEmail(user2.getEmail());
        user1.setAddress(user2.getAddress());
        user1.setAvatar(user.getAvatar());
        listFake.set(idx, user1);
        UserDto userDto = new UserDto();
        userDto.setId(id);
        userDto.setName(user2.getName());
        userDto.setAddress(user2.getAddress());
        userDto.setNumber(user2.getNumber());
        userDto.setEmail(user2.getEmail());
        userDto.setAvatar(user.getAvatar());
        return userDto;
    }

    public void updatePassword(Integer id1, UserDtoPass user) {
        int idx = -1;
        int id = 0;
        for (int i = 0; i < listFake.size(); i++) {
            if (listFake.get(i).getId() == (id1)) {
                id = id1;
                idx = i;
                if (listFake.get(i).getPassword().equals(user.getNewPassword())) {
                    throw new ErrorMessagePass(HttpStatus.BAD_REQUEST, "mật khẩu cũ giống mât khẩu mới");
                } else if (!listFake.get(i).getPassword().equals(user.getOldPassword())) {
                    throw new ErrorMessagePass(HttpStatus.BAD_REQUEST, "mật khẩu cũ không đúng");
                }
                break;
            }
        }
        if (idx == -1) {
            throw new ErrorMessagePass(HttpStatus.BAD_REQUEST, "không tìm thấy id " + id1);
        }
        User user2 = listFake.get(idx);

        User user1 = new User();
        user1.setId(id);
        user1.setName(user2.getName());
        user1.setNumber(user2.getNumber());
        user1.setEmail(user2.getEmail());
        user1.setAddress(user2.getAddress());
        user1.setAvatar(user2.getAvatar());
        user1.setPassword(user.getNewPassword());
        listFake.set(idx, user1);

    }


    public UserDtoPassNew fotgotPassword(Integer id1, UserDtoPassNew user) {
        int idx = -1;
        int id = 0;
        for (int i = 0; i < listFake.size(); i++) {
            if (listFake.get(i).getId() == (id1)) {
                id = id1;
                idx = i;
                break;
            }
        }
        if (idx == -1) {
            throw new ErrorMessagePass(HttpStatus.BAD_REQUEST, "không tìm thấy id " + id1);
        }
        User user2 = listFake.get(idx);
        Random rand = new Random();
        int ranNum = rand.nextInt(1000000) + 10000;
        User user1 = new User();
        user1.setId(id);
        user1.setName(user2.getName());
        user1.setNumber(user2.getNumber());
        user1.setEmail(user2.getEmail());
        user1.setAddress(user2.getAddress());
        user1.setAvatar(user2.getAvatar());
        user1.setPassword(String.valueOf(ranNum));
        listFake.set(idx, user1);
        UserDtoPassNew userDtoPassNew = new UserDtoPassNew();
        userDtoPassNew.setPassWordNew(String.valueOf(ranNum));
        return userDtoPassNew;
    }


    public Page<UserDto> findPaginated(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<UserDto> list=new ArrayList<>();

        if (listFake.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, listFake.size());
            List<User> userList = listFake.subList(startItem, toIndex);
            for(int i=0;i<userList.size();i++){
                UserDto userDto= new UserDto();
                userDto.setId(userList.get(i).getId());
                userDto.setName(userList.get(i).getName());
                userDto.setAddress(userList.get(i).getAddress());
                userDto.setNumber(userList.get(i).getNumber());
                userDto.setEmail(userList.get(i).getEmail());
                userDto.setAvatar(userList.get(i).getAvatar());
                list.add(userDto);
            }
        }

        Page<UserDto> coursePage
                = new PageImpl<>(list, PageRequest.of(currentPage, pageSize), listFake.size());
        return coursePage;
    }

}
