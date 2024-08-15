package com.africa.semicolon.web;

import com.africa.semicolon.DTO.request.ContactRequest;
import com.africa.semicolon.DTO.request.DeleteContactRequest;
import com.africa.semicolon.DTO.request.UpdateContactRequest;
import com.africa.semicolon.DTO.request.ViewContactRequest;
import com.africa.semicolon.DTO.response.*;
import com.africa.semicolon.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contact")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @PostMapping("/create")
    public ContactResponse createContact(@RequestBody ContactRequest contactRequest) {
        return contactService.createContact(contactRequest);
    }

    @GetMapping("/view")
    public ViewContactResponse viewContact(@RequestBody ViewContactRequest viewContactRequest) {
        return contactService.viewContact(viewContactRequest);
    }

    @PutMapping("/update")
    public UpdateContactResponse editContact(@RequestBody UpdateContactRequest updateContactRequest) {
        return contactService.editContact(updateContactRequest);
    }

    @GetMapping("/all")
    public ListOfContactResponse getAllContacts() {
        return contactService.getAllContacts();
    }

    @DeleteMapping("/delete")
    public DeleteContactResponse deleteContact(@RequestBody DeleteContactRequest deleteContactRequest) {
        return contactService.deleteContact(deleteContactRequest);
    }
}
