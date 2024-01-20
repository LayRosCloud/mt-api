package com.trans.api.repository;

import com.trans.api.entity.CounterpartyTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CounterpartyTypeRepository extends JpaRepository<CounterpartyTypeEntity, Short> {
}
