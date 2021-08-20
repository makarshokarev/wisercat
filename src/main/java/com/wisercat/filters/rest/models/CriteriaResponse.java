package com.wisercat.filters.rest.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CriteriaResponse {
  private String criteria;
  private String type;
  private String value;
}
