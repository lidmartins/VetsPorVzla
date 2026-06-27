package com.vetsportvzla.backend.service;

import com.vetsportvzla.backend.dto.ContactoDto;
import com.vetsportvzla.backend.repository.ContactoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ContactoServiceTest {

    @Mock
    private ContactoRepository contactoRepository;

    @InjectMocks
    private ContactoService contactoService;

    private ContactoDto contactoDto;

    @BeforeEach
    void setUp() {
        contactoDto = new ContactoDto(1, "Jane", "Doe", "jane.doe@example.com", "1234567890", "1234567890", "W", "S", new Date(), new Date());
    }

    @Test
    void createContacto() {
        when(contactoRepository.createContacto(any(ContactoDto.class))).thenReturn(contactoDto);
        ContactoDto created = contactoService.createContacto(contactoDto);
        assertEquals(contactoDto, created);
        verify(contactoRepository, times(1)).createContacto(contactoDto);
    }

    @Test
    void updateContacto() {
        when(contactoRepository.updateContacto(any(ContactoDto.class))).thenReturn(contactoDto);
        ContactoDto updated = contactoService.updateContacto(contactoDto);
        assertEquals(contactoDto, updated);
        verify(contactoRepository, times(1)).updateContacto(contactoDto);
    }

    @Test
    void deleteContacto() {
        doNothing().when(contactoRepository).deleteContacto(1);
        contactoService.deleteContacto(1);
        verify(contactoRepository, times(1)).deleteContacto(1);
    }

    @Test
    void searchContactos() {
        when(contactoRepository.searchContactos(any(), any(), any(), any())).thenReturn(List.of(contactoDto));
        List<ContactoDto> contactos = contactoService.searchContactos(1, "Jane", "Doe", "jane.doe@example.com");
        assertEquals(1, contactos.size());
        assertEquals(contactoDto, contactos.get(0));
        verify(contactoRepository, times(1)).searchContactos(1, "Jane", "Doe", "jane.doe@example.com");
    }
}
