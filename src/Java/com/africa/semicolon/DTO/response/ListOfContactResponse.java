package com.africa.semicolon.DTO.response;

import com.africa.semicolon.data.models.Contact;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ListOfContactResponse {
   private  List<Contact> contacts;
}
