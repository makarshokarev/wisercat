package com.wisercat.filters.persistence.repository;

import com.wisercat.filters.persistence.entity.Criteria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CriteriaRepository extends JpaRepository<Criteria, Long> {

}
