package com.wisercat.filters.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.wisercat.filters.persistence.entity.Filter;

@Repository
public interface FilterRepository extends JpaRepository<Filter, Long> {

}
