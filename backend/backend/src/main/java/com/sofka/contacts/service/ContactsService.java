package com.sofka.contacts.service;

import com.sofka.contacts.domain.Contact;
import com.sofka.contacts.repository.ContactRepository;
import com.sofka.contacts.service.interfaces.IContacts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class ContactsService implements IContacts {

    @Autowired
    private ContactRepository contactRepository;

    /**
     * Shows the non-deleted contacts
     * @return List of contacts
     */
    @Override
    @Transactional(readOnly = true)
    public List<Contact> getList() {
        return contactRepository.findList(false);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Contact> getContact(Integer id) {
        return contactRepository.findById(id);
    }

    /**
     * Shows the contacts sorted by a field
     * @param field field to sort
     * @param order order to sort
     * @return list
     */
    @Override
    @Transactional(readOnly = true)
    public List<Contact> getList(String field, Sort.Direction order) {
        return contactRepository.findAll(Sort.by(order,field));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Contact> getDeletedList() {
        return contactRepository.findList(true);
    }

    /**
     * Searches contact by phone, firstname and lastname
     * @param dataToSearch string with the data to search
     * @return list of contacts which fulfill the condition
     */
    @Override
    @Transactional(readOnly = true)
    public List<Contact> searchContact(String dataToSearch) {
        var answer = new HashSet<Contact>();
        try{
            long phone = Long.parseLong(dataToSearch);
            var contact1 = contactRepository.findByPhone(phone);
            answer.addAll(contact1);
        } catch (Exception exc){
            var contact1 = contactRepository.findByFLNameStartWith(dataToSearch);
            var contact2 = contactRepository.findByFLNameContain(dataToSearch);
            var contact3 = contactRepository.findByFLNameEndWith(dataToSearch);
            answer.addAll(contact1);
            answer.addAll(contact2);
            answer.addAll(contact3);
            System.out.println(exc);
        }

        return answer.stream().toList();
    }

    @Override
    @Transactional
    public Contact createContact(Contact contact) {
        contact.setCreatedAt(Instant.now());
        return contactRepository.save(contact);
    }

    @Override
    @Transactional
    public Contact updateContact(Integer id, Contact contact) {
        contact.setId(id);
        contact.setUpdatedAt(Instant.now());
        return contactRepository.save(contact);
    }

    @Override
    @Transactional
    public Contact updateFirstName(Integer id, Contact contact) {
        contact.setId(id);
        contact.setUpdatedAt(Instant.now());
        contactRepository.updateFirstname(id, contact.getFirstname());
        return contact;
    }

    @Override
    @Transactional
    public Contact updateLastName(Integer id, Contact contact) {
        contact.setId(id);
        contact.setUpdatedAt(Instant.now());
        contactRepository.updateLastname(id, contact.getLastname());
        return contact;
    }

    @Override
    @Transactional
    public Contact updateBirthday(Integer id, Contact contact) {
        contact.setId(id);
        contact.setUpdatedAt(Instant.now());
        contactRepository.updateBirthday(id, contact.getBirthday());
        return contact;
    }

    @Override
    @Transactional
    public Contact updatePhone(Integer id, Contact contact) {
        contact.setId(id);
        contact.setUpdatedAt(Instant.now());
        contactRepository.updatePhone(id, contact.getPhone());
        return contact;
    }

    /**
     * Delete an item
     * @param id id item to delete
     * @return the item deleted
     */
    @Override
    @Transactional
    public Contact deleteContact(Integer id) {
        var contact = contactRepository.findById(id);
        if (contact.isPresent()) {
            contactRepository.delete(contact.get());
            return contact.get();
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public Contact changeHidden(Integer id, Contact contact,Boolean hidden){
        contact.setId(id);
        contact.setUpdatedAt(Instant.now());
        contactRepository.updateHidden(id,hidden);
        return contact;
    }

}
