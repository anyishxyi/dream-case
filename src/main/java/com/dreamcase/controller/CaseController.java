package com.dreamcase.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dreamcase.model.Case;
import com.dreamcase.service.CaseService;

import javax.validation.Valid;

@RestController
@RequestMapping("/cases")
public class CaseController {

  @Autowired
  private CaseService caseService;

  /**
   * Retrieve a case by its ID.
   *
   * @param id The ID of the case to retrieve.
   * @return The Case object with the specified ID.
   */
  @GetMapping("/{id}")
  public ResponseEntity<Case> getCaseById(@PathVariable("id") String id) {
    Case retrievedCase = caseService.getCaseById(id);
    return ResponseEntity.ok(retrievedCase);
  }

  /**
   * Create a new case.
   *
   * @param caseInfo The Case object containing case information to be created.
   * @return The newly created Case object.
   */
  @PostMapping
  public ResponseEntity<Case> createCase(@RequestBody @Valid Case caseInfo) {
    Case createdCase = caseService.createCase(caseInfo);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdCase);
  }

  /**
   * Update an existing case with new information.
   *
   * @param id       The ID of the case to update.
   * @param caseInfo The Case object containing updated case information.
   * @return The updated Case object.
   */
  @PutMapping("/{id}")
  public ResponseEntity<Case> updateCase(@PathVariable("id") String id, @RequestBody @Valid Case caseInfo) {
    Case updatedCase = caseService.updateCase(id, caseInfo);
    return ResponseEntity.ok(updatedCase);
  }

  /**
   * Delete a case by its ID.
   *
   * @param id The ID of the case to delete.
   * @return ResponseEntity with no content (204) upon successful deletion.
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteCase(@PathVariable("id") String id) {
    caseService.deleteCase(id);
    return ResponseEntity.noContent().build();
  }
}
