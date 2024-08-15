package com.africa.semicolon.services;

import com.africa.semicolon.DTO.request.ContactRequest;
import com.africa.semicolon.DTO.request.DeleteContactRequest;
import com.africa.semicolon.DTO.request.UpdateContactRequest;
import com.africa.semicolon.DTO.request.ViewContactRequest;
import com.africa.semicolon.DTO.response.*;
import com.africa.semicolon.data.models.Contact;
import com.africa.semicolon.data.repositories.ContactRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactServiceImpl implements ContactService{

    private final ContactRepository contactRepository;

    public ContactServiceImpl (ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    public ContactResponse createContact(ContactRequest contactRequest) {
        Contact contact = new Contact();
        contact.setFirstName(contactRequest.getFirstName());
        contact.setLastName(contactRequest.getLastName());
        contact.setPhoneNumber(contactRequest.getPhoneNumber());
        contact.setEmail(contactRequest.getEmail());

        contactRepository.save(contact);

        return new ContactResponse("Contact created successfully");
    }

    @Override
    public ViewContactResponse viewContact(ViewContactRequest viewContactRequest) {
        List<Contact> contacts = contactRepository.findByPhoneNumber(viewContactRequest.getPhoneNumber());
        if (contacts.isEmpty()) {
            ViewContactResponse response = new ViewContactResponse();
            response.setMessage("Contact not found");
            return response;
        }
        Contact contact = contacts.get(0);
        ViewContactResponse response = new ViewContactResponse();
        response.setFirstName(contact.getFirstName());
        response.setLastName(contact.getLastName());
        response.setPhoneNumber(contact.getPhoneNumber());
        response.setEmail(contact.getEmail());
        return response;
    }

    @Override
    public UpdateContactResponse editContact(UpdateContactRequest updateContactRequest) {
        List<Contact> contacts = contactRepository.findByFirstName(updateContactRequest.getFirstName());
        if (contacts.isEmpty()) {
            UpdateContactResponse response = new UpdateContactResponse();
            response.setMessage("Contact not found");
            return response;
        }
        Contact contact = contacts.get(0);
        contact.setFirstName(updateContactRequest.getFirstName());
        contact.setLastName(updateContactRequest.getLastName());
        contact.setPhoneNumber(updateContactRequest.getPhoneNumber());
        contact.setEmail(updateContactRequest.getEmail());

        contactRepository.save(contact);

        UpdateContactResponse response = new UpdateContactResponse();
        response.setMessage("Contact updated successfully");
        return response;
    }

    @Override
    public ListOfContactResponse getAllContacts() {
        List<Contact> contacts = contactRepository.findAll();
        ListOfContactResponse allContacts = new ListOfContactResponse();
        allContacts.setContacts(contacts);
        return allContacts;
    }

    @Override
    public DeleteContactResponse deleteContact(DeleteContactRequest deleteContactRequest) {
        List<Contact> contacts = contactRepository.findByPhoneNumber(deleteContactRequest.getPhoneNumber());
        if (contacts.isEmpty()) {
            DeleteContactResponse response = new DeleteContactResponse();
            response.setMessage("Contact not found");
            return response;
        }
        Contact contact = contacts.get(0);
        contactRepository.delete(contact);

        DeleteContactResponse response = new DeleteContactResponse();
        response.setMessage("Contact deleted successfully");
        return response;
    }
}
