package com.wisercat.filters.rest.models;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseFilter {

  private String filterName;
  private List<CriteriaResponse> filters;
}
