package com.dreamcase.controller;

import com.dreamcase.model.Case;
import com.dreamcase.service.CaseService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import javax.persistence.EntityNotFoundException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CaseController.class)
class CaseControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @MockBean
  private CaseService caseService;

  @Test
  void testGetCaseById() throws Exception {
    Case testCase = new Case();
    testCase.setCaseId("E01");
    testCase.setCreationDate(LocalDateTime.now());
    testCase.setLastUpdateDate(LocalDateTime.now());
    testCase.setTitle("Test Case");
    testCase.setDescription("This is a test case");

    when(caseService.getCaseById("E01")).thenReturn(testCase);

    mockMvc.perform(get("/cases/E01"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.title").value("Test Case"))
            .andExpect(jsonPath("$.description").value("This is a test case"));
  }

  @Test
  void testGetCaseByNonExistentId() throws Exception {
    when(caseService.getCaseById("E02")).thenThrow(new EntityNotFoundException("Case not found with ID: E02" ));

    mockMvc.perform(get("/cases/E02"))
            .andExpect(status().isNotFound());
  }

  @Test
  void testCreateCase() throws Exception {
    Case testCase = new Case();
    testCase.setCaseId("E01");
    testCase.setCreationDate(LocalDateTime.now());
    testCase.setLastUpdateDate(LocalDateTime.now());
    testCase.setTitle("Test Case");
    testCase.setDescription("This is a test case");

    when(caseService.createCase(any())).thenReturn(testCase);

    mockMvc.perform(post("/cases")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(testCase)))
            .andExpect(status().isCreated())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.title").value("Test Case"))
            .andExpect(jsonPath("$.description").value("This is a test case"));
  }
}

