package com.assignment.ziploan.controller;

import com.assignment.ziploan.dto.external.ExternalContactDto;
import com.assignment.ziploan.exception.BadRequestException;
import com.assignment.ziploan.exception.NotFoundException;
import com.assignment.ziploan.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/contact")
public class ContactController {

    @Autowired
    ContactService contactService;

    @RequestMapping(
            value = "/",
            method = RequestMethod.GET
    )
    public ResponseEntity<?> getContacts(@RequestParam(value = "offset", required = false, defaultValue = "0") int offset,
                                            @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        return new ResponseEntity<>(contactService.getContacts(offset, size), HttpStatus.OK);
    }

    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.GET
    )
    public ResponseEntity<?> getContactById(@PathVariable("id") long id) throws NotFoundException {
        return new ResponseEntity<>(contactService.getContactById(id), HttpStatus.OK);
    }

    @RequestMapping(
            value = "/create",
            method = RequestMethod.POST
    )
    public ResponseEntity<?> createContact(@RequestBody ExternalContactDto contactDto) throws BadRequestException {
        return new ResponseEntity<>(contactService.saveContact(contactDto), HttpStatus.OK);
    }

    @RequestMapping(
            value = "/{id}/update",
            method = RequestMethod.PUT
    )
    public ResponseEntity<?> updateContact(
            @PathVariable("id") long contactId,
            @RequestBody ExternalContactDto contactDto) throws BadRequestException, NotFoundException {
        return new ResponseEntity<>(contactService.updateContact(contactId, contactDto), HttpStatus.OK);
    }


    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.DELETE
    )
    public ResponseEntity<?> deleteContactById(@PathVariable("id") long id) {
        contactService.deleteContactById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(
            value = "/search",
            method = RequestMethod.GET
    )
    public ResponseEntity<?> getContacts(@RequestParam String query) {
        return new ResponseEntity<>(contactService.getContactsByQuery(query), HttpStatus.OK);
    }


}
