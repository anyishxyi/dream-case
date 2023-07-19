package com.dreamcase.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.dreamcase.model.Case;
import com.dreamcase.repository.CaseRepository;

import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CaseServiceTest {

  @Mock
  private CaseRepository caseRepository;

  @InjectMocks
  private CaseService caseService;

  @Test
  void testGetCaseById() {
    String testId = "EO1";
    Case testCase = new Case();
    testCase.setCaseId(testId);
    testCase.setTitle("Test Case");
    testCase.setDescription("This is a test case.");

    when(caseRepository.findById(testId)).thenReturn(Optional.of(testCase));

    Case retrievedCase = caseService.getCaseById(testId);

    assertNotNull(retrievedCase);
    assertEquals(testId, retrievedCase.getCaseId());
  }

  @Test
  void testGetCaseById_CaseNotFound() {
    String nonExistentId = "100";

    when(caseRepository.findById(nonExistentId)).thenReturn(Optional.empty());

    assertThrows(RuntimeException.class, () -> caseService.getCaseById(nonExistentId));
  }

  @Test
  void testCreateCase() {
    Case testCase = new Case();
    testCase.setCaseId("E01");
    testCase.setTitle("Test Case");
    testCase.setDescription("This is a test case.");

    when(caseRepository.save(testCase)).thenReturn(testCase);

    Case createdCase = caseService.createCase(testCase);

    assertNotNull(createdCase.getCaseId());
    assertEquals(testCase.getTitle(), createdCase.getTitle());
    assertEquals(testCase.getDescription(), createdCase.getDescription());
  }

    @Test
    void testUpdateCase() {
      String testId = "E01";
      Case existingCase = new Case();
      existingCase.setCaseId(testId);
      existingCase.setTitle("Existing Case");
      existingCase.setDescription("This is an existing case.");

      Case updatedCase = new Case();
      updatedCase.setTitle("Updated Case");
      updatedCase.setDescription("This is an updated case.");

      when(caseRepository.findById(testId)).thenReturn(Optional.of(existingCase));
      when(caseRepository.save(existingCase)).thenReturn(existingCase);

      Case result = caseService.updateCase(testId, updatedCase);

      assertEquals(testId, result.getCaseId());
      assertEquals(updatedCase.getTitle(), result.getTitle());
      assertEquals(updatedCase.getDescription(), result.getDescription());
    }

    @Test
    void testUpdateCase_CaseNotFound() {
      String nonExistentId = "E02";
      Case updatedCase = new Case();
      updatedCase.setTitle("Updated Case");
      updatedCase.setDescription("This is an updated case.");

      when(caseRepository.findById(nonExistentId)).thenReturn(Optional.empty());

      assertThrows(RuntimeException.class, () -> caseService.updateCase(nonExistentId, updatedCase));
    }

    @Test
    void testDeleteCase() {
      String testId = "E01";
      Case testCase = new Case();
      testCase.setCaseId(testId);
      testCase.setTitle("Test Case");
      testCase.setDescription("This is a test case.");

      when(caseRepository.findById(testId)).thenReturn(Optional.of(testCase));

      caseService.deleteCase(testId);

      verify(caseRepository, times(1)).delete(testCase);
    }

    @Test
    void testDeleteCase_CaseNotFound() {
      String nonExistentId = "E02";

      when(caseRepository.findById(nonExistentId)).thenReturn(Optional.empty());

      assertThrows(RuntimeException.class, () -> caseService.deleteCase(nonExistentId));
    }
}
