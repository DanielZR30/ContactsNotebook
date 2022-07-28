package com.sofka.contacts.service.interfaces;

import com.sofka.contacts.domain.Contact;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface IContacts {

    public List<Contact> getList();

    public Optional<Contact> getContact(Integer id);

    public List<Contact> getList(String field, Sort.Direction order);

    public List<Contact> getDeletedList();

    public List<Contact> searchContact(String dataToSearch);

    public Contact createContact(Contact contacto);

    public Contact updateContact(Integer id, Contact contacto);

    public Contact updateFirstName(Integer id, Contact contacto);

    public Contact updateLastName(Integer id, Contact contacto);

    public Contact updatePhone(Integer id, Contact contacto);

    public Contact updateBirthday(Integer id, Contact contacto);

    public Contact deleteContact(Integer id);

    public Contact changeHidden(Integer id, Contact contact,Boolean hidden);
}
