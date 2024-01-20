package com.trans.api.repository;

import com.trans.api.entity.CounterpartyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CounterpartyRepository extends JpaRepository<CounterpartyEntity, Short> {
}
