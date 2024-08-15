package com.africa.semicolon.services;

import com.africa.semicolon.DTO.request.ContactRequest;
import com.africa.semicolon.DTO.request.DeleteContactRequest;
import com.africa.semicolon.DTO.request.UpdateContactRequest;
import com.africa.semicolon.DTO.request.ViewContactRequest;
import com.africa.semicolon.DTO.response.*;

import java.util.List;

public interface ContactService {
    ContactResponse createContact (ContactRequest contactRequest);
    ViewContactResponse viewContact (ViewContactRequest viewContactRequest);
    UpdateContactResponse editContact (UpdateContactRequest updateContactRequest);
    ListOfContactResponse getAllContacts();
    DeleteContactResponse deleteContact (DeleteContactRequest deleteContactRequest);
}
