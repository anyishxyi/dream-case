package com.dreamcase.model;

import java.time.LocalDateTime;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cases")
public class Case {
  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
  private String caseId;
  private LocalDateTime creationDate;
  private LocalDateTime lastUpdateDate;
  private String title;
  @Column(length = 2056)
  private String description;
}
