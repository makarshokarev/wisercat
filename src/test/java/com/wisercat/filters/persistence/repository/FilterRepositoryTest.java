package com.wisercat.filters.persistence.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import com.wisercat.filters.persistence.entity.Amount;
import com.wisercat.filters.persistence.entity.Date;
import com.wisercat.filters.persistence.entity.Filter;
import com.wisercat.filters.persistence.entity.Title;

@Commit
@SpringBootTest
class FilterRepositoryTest {

  @Autowired
  private FilterRepository filterRepository;
  @Autowired
  private CriteriaRepository criteriaRepository;
  @Autowired
  private TypeRepository typeRepository;

  @Transactional
  @Test
  void findFilterById() {

    final Filter filter = filterRepository.getById(1L);

    List<Amount> amounts = filter.getAmounts();
    assertEquals(2, amounts.size());

    for (Amount amount : amounts) {
      assertEquals(filter.getId(), amount.getFilter().getId());
    }

    assertEquals(Long.valueOf(100), amounts.get(0).getNumber());
    assertEquals(Long.valueOf(999), amounts.get(1).getNumber());

  }

  @Transactional
  @Test
  void saveFilter() {

    final Filter filter = new Filter(null, UUID.randomUUID().toString(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

    final Amount amount = new Amount(null, filter, criteriaRepository.getById(1L), typeRepository.getById(1L), 123L);
    final Title title = new Title(null, filter, criteriaRepository.getById(2L), typeRepository.getById(3L), "TEST");
    final Date date = new Date(null, filter, criteriaRepository.getById(3L), typeRepository.getById(5L), Calendar.getInstance().getTime());

    filter.getAmounts().add(amount);;
    filter.getTitles().add(title);
    filter.getDates().add(date);

    filterRepository.save(filter);
  }

}