package com.example.application.data.service;

import com.example.application.data.entity.Company;
import com.example.application.data.entity.Contact;
import com.example.application.data.entity.Status;
import com.example.application.data.repository.CompanyRepository;
import com.example.application.data.repository.ContactRepository;
import com.example.application.data.repository.StatusRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record CRMService(ContactRepository contactRepository,
                         CompanyRepository companyRepository,
                         StatusRepository statusRepository) {

    public List<Contact> findAll() {
        return contactRepository.findAll ();
    }

    public List<Contact> findAllContacts(String stringFilter) {
        if (stringFilter == null || stringFilter.isEmpty ()) {
            return contactRepository.findAll ();
        } else {
            return contactRepository.search (stringFilter);
        }
    }

    public long countContacts() {
        return contactRepository.count ();
    }

    public void deleteContact(Contact contact) {
        contactRepository.delete (contact);

    }

    public void saveContact(Contact contact) {
        if (contact == null) {
            System.err.println ("Contact is null. Are you sure you have connected your form to the application?");
            return;
        }
        contactRepository.save (contact);
    }

    public List<Company> findAllCompanies() {
        return companyRepository.findAll ();
    }

    public List<Status> findAllStatuses() {
        return statusRepository.findAll ();
    }
}
