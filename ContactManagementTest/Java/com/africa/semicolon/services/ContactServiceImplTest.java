package com.africa.semicolon.services;

import com.africa.semicolon.DTO.request.ContactRequest;
import com.africa.semicolon.DTO.request.DeleteContactRequest;
import com.africa.semicolon.DTO.request.UpdateContactRequest;
import com.africa.semicolon.DTO.request.ViewContactRequest;
import com.africa.semicolon.DTO.response.*;
import com.africa.semicolon.data.models.Contact;
import com.africa.semicolon.data.repositories.ContactRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ContactServiceImplTest {

    @Autowired
    private ContactService contactService;
    @Autowired
    private ContactRepository contactRepository;

    @Test
    public void testCreateContact() {
        ContactRequest request = new ContactRequest();
        request.setFirstName("Peter");
        request.setLastName("Daniel");
        request.setPhoneNumber("07012345678");
        request.setEmail("peter@gmail.com");

        ContactResponse response = contactService.createContact(request);

        assertThat(response.getMessage()).isEqualTo("Contact created successfully");
    }

    @Test
    public void testViewContact() {
        Contact contact = new Contact();
        contact.setFirstName("John");
        contact.setLastName("Dan");
        contact.setPhoneNumber("08012376549");
        contact.setEmail("john@gmail.com");
        contactRepository.save(contact);

        ViewContactRequest request = new ViewContactRequest();
        request.setPhoneNumber("08012376549");

        ViewContactResponse response = contactService.viewContact(request);
        assertThat(response.getFirstName()).isEqualTo("John");
        assertThat(response.getLastName()).isEqualTo("Dan");
        assertThat(response.getPhoneNumber()).isEqualTo("08012376549");
        assertThat(response.getEmail()).isEqualTo("john@gmail.com");
    }

    @Test
    public void testContactNotFound() {
        ViewContactRequest request = new ViewContactRequest();
        request.setPhoneNumber("08035028672");

        ViewContactResponse response = contactService.viewContact(request);
        assertThat(response.getMessage()).isEqualTo("Contact not found");
    }

    @Test
    public void testEditContact() {
        Contact contact = new Contact();
        contact.setFirstName("Kenneth");
        contact.setLastName("Beck");
        contact.setPhoneNumber("08012376548");
        contact.setEmail("beck@gmail.com");
        contactRepository.save(contact);

        UpdateContactRequest request = new UpdateContactRequest();
        request.setFirstName("Kenneth");
        request.setLastName("Beck");
        request.setPhoneNumber("08012376550");
        request.setEmail("kent@gmail.com");

        UpdateContactResponse response = contactService.editContact(request);
        assertThat(response.getMessage()).isEqualTo("Contact updated successfully");
    }

    @Test
    public void testEditContactNotFound() {
        UpdateContactRequest request = new UpdateContactRequest();
        request.setFirstName("Israel");

        UpdateContactResponse response = contactService.editContact(request);
        assertThat(response.getMessage()).isEqualTo("Contact not found");
    }

    @Test
    public void testGetAllContact() {
        int initialCount = contactRepository.findAll().size();

        Contact contact1 = new Contact();
        contact1.setFirstName("John");
        contact1.setLastName("Doe");
        contact1.setPhoneNumber("1234567890");
        contact1.setEmail("john.doe@example.com");
        contactRepository.save(contact1);

        Contact contact2 = new Contact();
        contact2.setFirstName("Jane");
        contact2.setLastName("Doe");
        contact2.setPhoneNumber("0987654321");
        contact2.setEmail("jane.doe@example.com");
        contactRepository.save(contact2);

        ListOfContactResponse response = contactService.getAllContacts();

        assertEquals(initialCount + 2, response.getContacts().size());
    }

    @Test
    public void testDeleteContact() {
        Contact contact = new Contact();
        contact.setFirstName("Smart");
        contact.setLastName("Sunny");
        contact.setPhoneNumber("08038004434");
        contact.setEmail("smart.doe@example.com");
        contactRepository.save(contact);

        DeleteContactRequest request = new DeleteContactRequest();
        request.setPhoneNumber("08038004434");

        DeleteContactResponse response = contactService.deleteContact(request);

        assertThat(response.getMessage()).isEqualTo("Contact deleted successfully");
    }

    @Test
    public void DeleteContactNotFound() {
        DeleteContactRequest request = new DeleteContactRequest();
        request.setPhoneNumber("08035005434");

        DeleteContactResponse response = contactService.deleteContact(request);

        assertThat(response.getMessage()).isEqualTo("Contact not found");
    }
}
