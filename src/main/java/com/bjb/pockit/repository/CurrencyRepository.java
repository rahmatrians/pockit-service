package com.bjb.pockit.repository;

import com.bjb.pockit.entity.CurrenciesWILLREMOVE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CurrencyRepository extends JpaRepository<CurrenciesWILLREMOVE, Long> {
    List<CurrenciesWILLREMOVE> findAllByCode(String code);

    Optional<CurrenciesWILLREMOVE> findById(Long id);

}
