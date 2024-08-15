package com.africa.semicolon.data.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Contact {
    private String id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
}
