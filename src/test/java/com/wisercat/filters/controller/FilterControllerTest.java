package com.wisercat.filters.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.wisercat.filters.persistence.entity.Filter;
import com.wisercat.filters.rest.models.RequestFilter;
import com.wisercat.filters.rest.models.ResponseFilter;
import com.wisercat.filters.rest.models.RestCriteria;
import com.wisercat.filters.rest.models.RestType;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.wisercat.filters.rest.service.FilterService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class FilterControllerTest {

  private final Long ID = 1L;
  private final String NAME = "test";
  private final String TYPE = "AMOUNT";

  @Autowired
  private MockMvc mockMvc;

  @MockBean

  FilterService filterService;

  @Test
  public void addFilterReturn201() throws Exception {
    Filter filter = new Filter();
    filter.setId(ID);
    filter.setFilterName(NAME);

    RequestFilter requestFilter = new RequestFilter();
    requestFilter.setFilterName(NAME);

    ObjectMapper mapper = new ObjectMapper();
    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
    ObjectWriter objectWriter = mapper.writer().withDefaultPrettyPrinter();
    String requestJson = objectWriter.writeValueAsString(requestFilter);

    when(filterService.saveFilter(any())).thenReturn(filter);

    mockMvc.perform(post("/filter").content(requestJson).contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.filterName").value(NAME));

    verify(filterService, times(1)).saveFilter(any(RequestFilter.class));
    verifyNoMoreInteractions(filterService);
  }

  @Test
  public void addFilterReturn400() throws Exception {
    Filter filter = null;
    ObjectMapper mapper = new ObjectMapper();
    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
    ObjectWriter objectWriter = mapper.writer().withDefaultPrettyPrinter();
    String requestJson = objectWriter.writeValueAsString(filter);

    mockMvc.perform(post("/filter").content(requestJson).contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void getAllFiltersReturn200() throws Exception {
    List<ResponseFilter> responseFilterList = new ArrayList<>();
    ResponseFilter responseFilter = new ResponseFilter();
    responseFilter.setFilterName(NAME);
    responseFilterList.add(responseFilter);

    when(filterService.getAllFilters()).thenReturn(responseFilterList);

    mockMvc.perform(get("/filter"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$[0].filterName").value(NAME));

    verify(filterService, times(1)).getAllFilters();
    verifyNoMoreInteractions(filterService);
  }

  @Test
  public void getAllFiltersReturn404() throws Exception{
    List<ResponseFilter> responseFilterList = new ArrayList<>();

    when(filterService.getAllFilters()).thenReturn(responseFilterList);

    mockMvc.perform(get("/filter"))
        .andExpect(status().isNotFound())
        .andExpect(content().contentType(MediaType.APPLICATION_PROBLEM_JSON));

    verify(filterService, times(1)).getAllFilters();
    verifyNoMoreInteractions(filterService);
  }

  @Test
  public void getAllCriteriesReturn200() throws Exception {
    List<RestCriteria> restCriteriaList = new ArrayList<>();
    RestCriteria restCriteria = new RestCriteria();
    restCriteria.setCriteriaName(NAME);
    restCriteriaList.add(restCriteria);

    when(filterService.getCriteries()).thenReturn(restCriteriaList);

    mockMvc.perform(get("/criteries"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$[0].criteriaName").value(NAME));

    verify(filterService, times(1)).getCriteries();
    verifyNoMoreInteractions(filterService);
  }

  @Test
  public void getCriteriesReturn404() throws Exception{
    List<RestCriteria> restCriteriaList = new ArrayList<>();

    when(filterService.getCriteries()).thenReturn(restCriteriaList);

    mockMvc.perform(get("/criteries"))
        .andExpect(status().isNotFound())
        .andExpect(content().contentType(MediaType.APPLICATION_PROBLEM_JSON));

    verify(filterService, times(1)).getCriteries();
    verifyNoMoreInteractions(filterService);
  }

  @Test
  public void getTypesReturn200() throws Exception {
    List<RestType> restTypeList = new ArrayList<>();
    RestType restType = new RestType();
    restType.setTypeName(TYPE);
    restTypeList.add(restType);

    when(filterService.getTypes(ID)).thenReturn(restTypeList);

    mockMvc.perform(get("/type/" + ID))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$[0].typeName").value(TYPE));

    verify(filterService, times(1)).getTypes(ID);
    verifyNoMoreInteractions(filterService);
  }

  @Test
  public void getTypesReturn404() throws Exception{
    List<RestType> restTypeList = new ArrayList<>();

    when(filterService.getTypes(ID)).thenReturn(restTypeList);

    mockMvc.perform(get("/type/" + ID))
        .andExpect(status().isNotFound())
        .andExpect(content().contentType(MediaType.APPLICATION_PROBLEM_JSON));

    verify(filterService, times(1)).getTypes(ID);
    verifyNoMoreInteractions(filterService);
  }
}
