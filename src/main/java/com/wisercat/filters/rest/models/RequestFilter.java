package com.wisercat.filters.rest.models;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestFilter {

  private String filterName;
  private List<CriteriaRequest> filters;
}
