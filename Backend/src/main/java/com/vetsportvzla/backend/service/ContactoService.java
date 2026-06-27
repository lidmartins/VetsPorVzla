package com.vetsportvzla.backend.service;

import com.vetsportvzla.backend.dto.ContactoDto;
import com.vetsportvzla.backend.repository.ContactoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactoService {

    private final ContactoRepository contactoRepository;

    public ContactoService(ContactoRepository contactoRepository) {
        this.contactoRepository = contactoRepository;
    }

    public ContactoDto createContacto(ContactoDto contacto) {
        return contactoRepository.createContacto(contacto);
    }

    public ContactoDto updateContacto(ContactoDto contacto) {
        return contactoRepository.updateContacto(contacto);
    }

    public void deleteContacto(int contactoId) {
        contactoRepository.deleteContacto(contactoId);
    }

    public List<ContactoDto> searchContactos(Integer contactoId, String firstName, String lastName, String email) {
        return contactoRepository.searchContactos(contactoId, firstName, lastName, email);
    }
}
