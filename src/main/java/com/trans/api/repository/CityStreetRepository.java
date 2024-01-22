package com.trans.api.repository;

import com.trans.api.entity.CityStreetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CityStreetRepository extends JpaRepository<CityStreetEntity, Integer> {
    Optional<CityStreetEntity> findByCityIdAndStreetId(Integer cityId, Integer streetId);
    List<CityStreetEntity> findAllByCityId(Integer cityId);
}
