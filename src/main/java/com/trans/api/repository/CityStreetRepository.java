package com.trans.api.repository;

import com.trans.api.entity.CityStreetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityStreetRepository extends JpaRepository<CityStreetEntity, Integer> {
}
