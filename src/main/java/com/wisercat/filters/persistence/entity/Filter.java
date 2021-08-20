package com.wisercat.filters.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Filter {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "filter_name", nullable = false, unique = true, length = 50)
  private String filterName;

  @OneToMany(mappedBy = "filter", cascade = CascadeType.ALL)
  private List<Amount> amounts = new ArrayList<>();

  @OneToMany(mappedBy = "filter", cascade = CascadeType.ALL)
  private List<Title> titles = new ArrayList<>();

  @OneToMany(mappedBy = "filter", cascade = CascadeType.ALL)
  private List<Date> dates = new ArrayList<>();



}
