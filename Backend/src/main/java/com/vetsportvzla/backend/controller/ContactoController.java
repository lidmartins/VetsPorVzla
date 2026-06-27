package com.vetsportvzla.backend.controller;

import com.vetsportvzla.backend.dto.ContactoDto;
import com.vetsportvzla.backend.service.ContactoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contacto")
public class ContactoController {

    private final ContactoService contactoService;

    public ContactoController(ContactoService contactoService) {
        this.contactoService = contactoService;
    }

    @PostMapping
    public ResponseEntity<ContactoDto> createContacto(@RequestBody ContactoDto contacto) {
        ContactoDto createdContacto = contactoService.createContacto(contacto);
        return ResponseEntity.ok(createdContacto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContactoDto> updateContacto(@PathVariable int id, @RequestBody ContactoDto contacto) {
        contacto.setCoCdContacto(id);
        ContactoDto updatedContacto = contactoService.updateContacto(contacto);
        return ResponseEntity.ok(updatedContacto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContacto(@PathVariable int id) {
        contactoService.deleteContacto(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<ContactoDto>> searchContactos(
            @RequestParam(required = false) Integer id,
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String email) {
        List<ContactoDto> contactos = contactoService.searchContactos(id, firstName, lastName, email);
        return ResponseEntity.ok(contactos);
    }
}
