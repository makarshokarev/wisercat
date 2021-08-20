package com.wisercat.filters.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.wisercat.filters.persistence.entity.Title;

@Repository
public interface TitleRepository extends JpaRepository<Title, Long> {

}
