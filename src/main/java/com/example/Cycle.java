package com.example;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Entity
public class Cycle {
  @Id private Integer id;


  @Enumerated(EnumType.STRING)
  private CycleType type;

  private LocalDate created;

  public Cycle() {}

  public Cycle(Integer id, CycleType type, LocalDate created) {
    this.id = id;
    this.type = type;
    this.created = created;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public CycleType getType() {
    return type;
  }

  public void setType(CycleType type) {
    this.type = type;
  }

  public LocalDate getCreated() {
    return created;
  }

  public void setCreated(LocalDate created) {
    this.created = created;
  }
}
