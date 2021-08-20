package com.wisercat.filters.persistence.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Title {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "filter_id", nullable = false)
  private Filter filter;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "criteria_id", nullable = false)
  private Criteria criteria;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "type_id", nullable = false)
  private Type type;

  @Column(name = "text")
  private String text;

}
