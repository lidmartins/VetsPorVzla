package com.vetsportvzla.backend.service;

import com.vetsportvzla.backend.dto.RevisionVeterinariaDto;
import com.vetsportvzla.backend.repository.RevisionVeterinariaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RevisionVeterinariaService {

    private final RevisionVeterinariaRepository revisionVeterinariaRepository;

    public RevisionVeterinariaService(RevisionVeterinariaRepository revisionVeterinariaRepository) {
        this.revisionVeterinariaRepository = revisionVeterinariaRepository;
    }

    public RevisionVeterinariaDto createRevisionVeterinaria(RevisionVeterinariaDto revision) {
        return revisionVeterinariaRepository.createRevisionVeterinaria(revision);
    }

    public RevisionVeterinariaDto updateRevisionVeterinaria(RevisionVeterinariaDto revision) {
        return revisionVeterinariaRepository.updateRevisionVeterinaria(revision);
    }

    public void deleteRevisionVeterinaria(int revisionId) {
        revisionVeterinariaRepository.deleteRevisionVeterinaria(revisionId);
    }

    public List<RevisionVeterinariaDto> searchRevisionesVeterinarias(Integer revisionId, Integer animalId, Integer userId, String status) {
        return revisionVeterinariaRepository.searchRevisionesVeterinarias(revisionId, animalId, userId, status);
    }
}
