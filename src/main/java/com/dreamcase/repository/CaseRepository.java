package com.dreamcase.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dreamcase.model.Case;

public interface CaseRepository extends JpaRepository<Case, String> {}
