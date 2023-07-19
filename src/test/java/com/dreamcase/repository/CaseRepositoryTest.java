package com.dreamcase.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.dreamcase.model.Case;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

@DataJpaTest
class CaseRepositoryTest {

    @Autowired
    private CaseRepository caseRepository;

    @Test
    void testSaveCase() {
        Case testCase = new Case();
        testCase.setCaseId("E01");
        testCase.setTitle("Test Case");
        testCase.setCreationDate(LocalDateTime.now());
        testCase.setLastUpdateDate(LocalDateTime.now());
        testCase.setDescription("This is a test case.");

        Case savedCase = caseRepository.save(testCase);
        assertNotNull(savedCase.getCaseId());
    }

    @Test
    void testFindById() {
        Case testCase = new Case();
        testCase.setCaseId("E01");
        testCase.setTitle("Test Case");
        testCase.setDescription("This is a test case.");

        Case savedCase = caseRepository.save(testCase);
        String savedCaseId = savedCase.getCaseId();

        Case foundCase = caseRepository.findById(savedCaseId).orElse(null);
        assertNotNull(foundCase);
        assertEquals(savedCaseId, foundCase.getCaseId());
    }

    @Test
    void testDeleteCase() {
        Case testCase = new Case();
        testCase.setCaseId("E01");
        testCase.setTitle("Test Case");
        testCase.setDescription("This is a test case.");

        Case savedCase = caseRepository.save(testCase);
        String savedCaseId = savedCase.getCaseId();

        caseRepository.deleteById(savedCaseId);

        assertFalse(caseRepository.findById(savedCaseId).isPresent());
    }
}
