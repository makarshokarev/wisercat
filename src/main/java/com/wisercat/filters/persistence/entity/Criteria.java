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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Criteria {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "criteria_name")
  private String criteriaName;

  @OneToMany(mappedBy = "criteria", cascade = CascadeType.ALL)
  private List<Amount> amounts = new ArrayList<>();

  @OneToMany(mappedBy = "criteria", cascade = CascadeType.ALL)
  private List<Title> titles = new ArrayList<>();

  @OneToMany(mappedBy = "criteria", cascade = CascadeType.ALL)
  private List<Date> dates = new ArrayList<>();
}
