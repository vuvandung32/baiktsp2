package com.example.baitapkt.userDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDtoPass {
    private String oldPassword;
    private String newPassword;

}
