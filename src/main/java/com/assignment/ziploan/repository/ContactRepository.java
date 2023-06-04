package com.assignment.ziploan.repository;

import com.assignment.ziploan.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ContactRepository extends JpaRepository<Contact, Long> {

    @Query(value = "SELECT * FROM ziploan.contact WHERE CAST(first_name AS CHAR)  LIKE ?1 OR CAST(last_name AS CHAR)  LIKE ?1 OR CAST(email AS CHAR)  LIKE ?1", nativeQuery = true)
    List<Contact> findContactsByKeyword(String query);
}
