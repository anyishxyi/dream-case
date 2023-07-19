package com.dreamcase.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dreamcase.model.Case;
import com.dreamcase.repository.CaseRepository;

import java.time.LocalDateTime;

import javax.persistence.EntityNotFoundException;

@Service
public class CaseService {
  @Autowired
  private CaseRepository caseRepository;
  private static final String NOT_FOUND_BY_ID_MSG = "Case not found with ID: ";

  public Case getCaseById(String id) {
    return caseRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(NOT_FOUND_BY_ID_MSG + id));
  }

  public Case createCase(Case caseInfo) {
    caseInfo.setCreationDate(LocalDateTime.now());
    caseInfo.setLastUpdateDate(LocalDateTime.now());
    return caseRepository.save(caseInfo);
  }

  public Case updateCase(String id, Case caseInfo) {
    Case existingCase = caseRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(NOT_FOUND_BY_ID_MSG + id));

    existingCase.setTitle(caseInfo.getTitle());
    existingCase.setDescription(caseInfo.getDescription());
    existingCase.setLastUpdateDate(LocalDateTime.now());

    return caseRepository.save(existingCase);
  }

  public void deleteCase(String id) {
    Case existingCase = caseRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(NOT_FOUND_BY_ID_MSG + id));

    caseRepository.delete(existingCase);
  }
}
