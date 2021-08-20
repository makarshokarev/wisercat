package com.wisercat.filters.persistence.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Type {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "type_name")
  private String typeName;

  @Column(name = "criteria_id")
  private Long criteriaId;

  @OneToMany(mappedBy = "type", cascade = CascadeType.ALL)
  private List<Amount> amounts = new ArrayList<>();

  @OneToMany(mappedBy = "type", cascade = CascadeType.ALL)
  private List<Title> titles = new ArrayList<>();

  @OneToMany(mappedBy = "type", cascade = CascadeType.ALL)
  private List<Date> dates = new ArrayList<>();
}
