package com.example.baitapkt.mode;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class User {

    private int id;
    private String name;
    private String email;
    private String number;
    private String address;
    private String avatar;
    private String password;



}
