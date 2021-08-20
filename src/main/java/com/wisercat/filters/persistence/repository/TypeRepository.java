package com.wisercat.filters.persistence.repository;

import com.wisercat.filters.persistence.entity.Type;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeRepository extends JpaRepository<Type, Long> {

  List<Type> findAllByCriteriaId(Long id);
}
