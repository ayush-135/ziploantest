package com.assignment.ziploan.service;

import com.assignment.ziploan.dto.external.ExternalContactDto;
import com.assignment.ziploan.exception.BadRequestException;
import com.assignment.ziploan.exception.NotFoundException;

import java.util.List;

public interface ContactService {

    ExternalContactDto getContactById(long id) throws NotFoundException;

    ExternalContactDto saveContact(ExternalContactDto contactDto) throws BadRequestException;

    void deleteContactById(long id);

    ExternalContactDto updateContact(long contactId, ExternalContactDto contactDto) throws BadRequestException, NotFoundException;

    List<ExternalContactDto> getContacts(int offset, int size);

    List<ExternalContactDto> getContactsByQuery(String query);
}
