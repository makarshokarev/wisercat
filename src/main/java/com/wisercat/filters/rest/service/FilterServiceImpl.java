package com.wisercat.filters.rest.service;

import com.wisercat.filters.persistence.entity.Amount;
import com.wisercat.filters.persistence.entity.Criteria;
import com.wisercat.filters.persistence.entity.Date;
import com.wisercat.filters.persistence.entity.Filter;
import com.wisercat.filters.persistence.entity.Title;
import com.wisercat.filters.persistence.entity.Type;
import com.wisercat.filters.persistence.repository.CriteriaRepository;
import com.wisercat.filters.persistence.repository.FilterRepository;
import com.wisercat.filters.persistence.repository.TypeRepository;
import com.wisercat.filters.rest.models.CriteriaRequest;
import com.wisercat.filters.rest.models.CriteriaResponse;
import com.wisercat.filters.rest.models.RequestFilter;
import com.wisercat.filters.rest.models.ResponseFilter;
import com.wisercat.filters.rest.models.RestCriteria;
import com.wisercat.filters.rest.models.RestType;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FilterServiceImpl implements FilterService {

  @Autowired
  private FilterRepository filterRepository;
  @Autowired
  private CriteriaRepository criteriaRepository;
  @Autowired
  private TypeRepository typeRepository;

  @Override
  public List<ResponseFilter> getAllFilters() {
    List<Filter> filters = filterRepository.findAll();
    List<ResponseFilter> newFilters = new ArrayList<>();

    for (Filter filter : filters) {
      ResponseFilter newFilter = new ResponseFilter();
      newFilter.setFilterName(filter.getFilterName());
      List<CriteriaResponse> compositeFilterList = new ArrayList<>();

      for (Amount amount : filter.getAmounts()) {
        CriteriaResponse compositeFilter = new CriteriaResponse();
        compositeFilter.setCriteria(amount.getCriteria().getCriteriaName());
        compositeFilter.setType(amount.getType().getTypeName());
        compositeFilter.setValue(amount.getNumber().toString());
        compositeFilterList.add(compositeFilter);
      }

      for (Title title : filter.getTitles()) {
        CriteriaResponse compositeFilter = new CriteriaResponse();
        compositeFilter.setCriteria(title.getCriteria().getCriteriaName());
        compositeFilter.setType(title.getType().getTypeName());
        compositeFilter.setValue(title.getText());
        compositeFilterList.add(compositeFilter);
      }

      for (Date date : filter.getDates()) {
        CriteriaResponse compositeFilter = new CriteriaResponse();
        compositeFilter.setCriteria(date.getCriteria().getCriteriaName());
        compositeFilter.setType(date.getType().getTypeName());
        compositeFilter.setValue(date.getDate().toString());
        compositeFilterList.add(compositeFilter);
      }

      newFilter.setFilters(compositeFilterList);
      newFilters.add(newFilter);
    }

    return newFilters;
  }

  @Override
  public Filter saveFilter(RequestFilter requestFilter) throws ParseException {
    Filter filter = new Filter(null, requestFilter.getFilterName(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    List<Amount> amounts = new ArrayList<>();
    List<Title> titles = new ArrayList<>();
    List<Date> dates = new ArrayList<>();
    for (CriteriaRequest compositeFilter : requestFilter.getFilters()) {
      if (compositeFilter.getCriteria().equals(1L)) {
        Amount amount = new Amount(null, filter, criteriaRepository.getById(compositeFilter.getCriteria()), typeRepository.getById(compositeFilter.getType()),
            Long.parseLong(compositeFilter.getValue()));
        amounts.add(amount);
      }
      if (compositeFilter.getCriteria().equals(2L)) {
        Title title = new Title(null, filter, criteriaRepository.getById(compositeFilter.getCriteria()), typeRepository.getById(compositeFilter.getType()), compositeFilter.getValue());
        titles.add(title);
      }
      if (compositeFilter.getCriteria().equals(3L)) {
        String stringDate = compositeFilter.getValue();
        java.util.Date newDate = new SimpleDateFormat("yyyy-MM-dd").parse(stringDate);
        Date date = new Date(null, filter, criteriaRepository.getById(compositeFilter.getCriteria()), typeRepository.getById(compositeFilter.getType()), newDate);
        dates.add(date);
      }
    }

    filter.setAmounts(amounts);
    filter.setTitles(titles);
    filter.setDates(dates);
    filterRepository.save(filter);

    return filter;
  }

  @Override
  public List<RestCriteria> getCriteries() {
    List<Criteria> allCriterion = criteriaRepository.findAll();

    List<RestCriteria> restCriteriaList = new ArrayList<>();
    allCriterion.forEach(criteria -> {
      RestCriteria restCriteria = new RestCriteria();
      restCriteria.setId(criteria.getId());
      restCriteria.setCriteriaName(criteria.getCriteriaName());
      restCriteriaList.add(restCriteria);
    });

    return restCriteriaList;
  }

  @Override
  public List<RestType> getTypes(Long id) {
    List<Type> allTypes = typeRepository.findAllByCriteriaId(id);

    List<RestType> restTypeList = new ArrayList<>();
    allTypes.forEach(types -> {
      RestType restType = new RestType();
      restType.setId(types.getId());
      restType.setTypeName(types.getTypeName());
      restTypeList.add(restType);
    });

    return restTypeList;
  }

}
