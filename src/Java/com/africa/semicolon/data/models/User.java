package com.africa.semicolon.data.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document
public class User {
    private String id;
    private String username;
    private String password;
    private String email;
    private ArrayList<Contact> contacts;
}