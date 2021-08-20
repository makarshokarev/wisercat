package com.wisercat.filters.rest.service;

import com.wisercat.filters.persistence.entity.Filter;
import com.wisercat.filters.rest.models.RequestFilter;
import com.wisercat.filters.rest.models.RestCriteria;
import com.wisercat.filters.rest.models.RestType;
import java.text.ParseException;
import java.util.List;
import com.wisercat.filters.rest.models.ResponseFilter;

public interface FilterService {
  List<ResponseFilter> getAllFilters();
  Filter saveFilter(RequestFilter requestFilter) throws ParseException;
  List<RestCriteria> getCriteries();
  List<RestType> getTypes(Long id);
}
