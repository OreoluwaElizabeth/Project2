package com.africa.semicolon.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ViewContactResponse {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String message;
}
