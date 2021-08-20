package com.wisercat.filters.controller;


import com.wisercat.filters.header.HeaderGenerator;
import com.wisercat.filters.persistence.entity.Filter;
import com.wisercat.filters.rest.models.RequestFilter;
import com.wisercat.filters.rest.models.ResponseFilter;
import com.wisercat.filters.rest.models.RestCriteria;
import com.wisercat.filters.rest.models.RestType;
import com.wisercat.filters.rest.service.FilterService;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class FiltersController {

  @Autowired
  private FilterService filterService;
  @Autowired
  private HeaderGenerator headerGenerator;

  @PostMapping("/filter")
  public ResponseEntity<Filter> addFilter(@RequestBody RequestFilter restFilter, HttpServletRequest request) {
    if (Objects.nonNull(restFilter)) {
      try {
        Filter filter = filterService.saveFilter(restFilter);
        return new ResponseEntity<>(filter, headerGenerator.getHeadersForSuccessPostMethod(request), HttpStatus.CREATED);
      } catch (Exception e){
        e.printStackTrace();
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }
    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
  }

  @GetMapping("/filter")
  public ResponseEntity<List<ResponseFilter>> getFilters() {
    List<ResponseFilter> filters = filterService.getAllFilters();
    if (!filters.isEmpty()) {
      return new ResponseEntity<>(filters, headerGenerator.getHeadersForSuccessGetMethod(), HttpStatus.OK);
    }
    return new ResponseEntity<>(headerGenerator.getHeadersForError(), HttpStatus.NOT_FOUND);
  }

  @GetMapping("/criteries")
  public ResponseEntity<List<RestCriteria>> getCriterion(){
    List<RestCriteria> criterion = filterService.getCriteries();
    if (!criterion.isEmpty()) {
      return new ResponseEntity<>(criterion, headerGenerator.getHeadersForSuccessGetMethod(), HttpStatus.OK);
    }
    return new ResponseEntity<>(headerGenerator.getHeadersForError(), HttpStatus.NOT_FOUND);
  }

  @GetMapping (value = "type/{id}")
  public ResponseEntity<List<RestType>> getTypes(@PathVariable Long id){
    List<RestType> types = filterService.getTypes(id);
    if (!types.isEmpty()) {
      return new ResponseEntity<>(types, headerGenerator.getHeadersForSuccessGetMethod(), HttpStatus.OK);
    }
    return new ResponseEntity<>(headerGenerator.getHeadersForError(), HttpStatus.NOT_FOUND);
  }

}
