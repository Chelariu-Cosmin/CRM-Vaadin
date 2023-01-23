package com.example.application.data.repository;

import com.example.application.data.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, Long> {

    @Query("select c from Contact c " +
            "where lower(c.firstName) like lower(concat('%', :stringFilter, '%')) " +
            "or lower(c.lastName) like lower(concat('%', :stringFilter, '%'))")
    List<Contact> search(String stringFilter);
}
