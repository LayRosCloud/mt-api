package com.trans.api.repository;

import com.trans.api.entity.UnitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UnitRepository extends JpaRepository<UnitEntity, Short> {

}
