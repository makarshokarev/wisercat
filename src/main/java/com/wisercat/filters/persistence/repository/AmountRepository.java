package com.wisercat.filters.persistence.repository;

import com.wisercat.filters.persistence.entity.Amount;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AmountRepository extends JpaRepository<Amount, Long> {
  List<Amount> findAllByFilterId(Long id);
}
