package com.assignment.ziploan.service;

import com.assignment.ziploan.dto.external.ExternalContactDto;
import com.assignment.ziploan.exception.BadRequestException;
import com.assignment.ziploan.exception.NotFoundException;
import com.assignment.ziploan.model.Contact;
import com.assignment.ziploan.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    ContactRepository contactRepository;

    @Override
    public ExternalContactDto getContactById(long id) throws NotFoundException {
        Optional<Contact> contactOptional = contactRepository.findById(id);
        if (contactOptional.isPresent()) {
            return convertContactEntityToDto(contactOptional.get());
        } else {
            throw new NotFoundException("Invalid Id");
        }
    }

    @Override
    public ExternalContactDto saveContact(ExternalContactDto contactDto) throws BadRequestException {
        if (contactDto == null || StringUtils.isEmpty(contactDto.getFirstName()) || StringUtils.isEmpty(contactDto.getPhoneNumber())) {
            throw new BadRequestException("First name and phone is required");
        }
        Contact contact = convertContactDtoToEntity(contactDto);
        contact = contactRepository.save(contact);
        return convertContactEntityToDto(contact);
    }

    @Override
    public void deleteContactById(long id) {
        contactRepository.deleteById(id);
    }

    @Override
    public ExternalContactDto updateContact(long contactId, ExternalContactDto contactDto) throws BadRequestException, NotFoundException {
        Optional<Contact> contactOptional = contactRepository.findById(contactId);
        if (!contactOptional.isPresent()) {
            throw new NotFoundException("Invalid Id");
        }
        contactDto.setId(contactId);
        return saveContact(contactDto);
    }

    @Override
    public List<ExternalContactDto> getContacts(int offset, int size) {
        Pageable pageable = PageRequest.of(offset, size);
        Page<Contact> contacts = contactRepository.findAll(pageable);
        if (contacts != null && !CollectionUtils.isEmpty(contacts.getContent())) {
            return contacts.get()
                    .map(_contact -> convertContactEntityToDto(_contact))
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    @Override
    public List<ExternalContactDto> getContactsByQuery(String query) {
        List<Contact> contactList = contactRepository.findContactsByKeyword(query);
        if (!CollectionUtils.isEmpty(contactList)) {
            return contactList.stream()
                    .map(_contact -> convertContactEntityToDto(_contact))
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }


    // instead of adapters for converting
    private ExternalContactDto convertContactEntityToDto(Contact contact) {
        if (contact != null) {
            return ExternalContactDto.builder()
                        .id(contact.getId())
                        .firstName(contact.getFirstName())
                        .lastName(contact.getLastName())
                        .email(contact.getEmail())
                        .phoneNumber(contact.getPhone())
                        .build();
        }
        return null;
    }

    // instead of adapters for converting
    private Contact convertContactDtoToEntity(ExternalContactDto dto) {
        if (dto != null) {
            return  Contact.builder()
                        .id(dto.getId())
                        .firstName(dto.getFirstName())
                        .lastName(dto.getLastName())
                        .email(dto.getEmail())
                        .phone(dto.getPhoneNumber())
                        .build();
        }
        return null;
    }
}
