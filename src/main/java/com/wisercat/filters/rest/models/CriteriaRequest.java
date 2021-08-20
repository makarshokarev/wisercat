package com.wisercat.filters.rest.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CriteriaRequest {

  private Long criteria;
  private Long type;
  private String value;

}
