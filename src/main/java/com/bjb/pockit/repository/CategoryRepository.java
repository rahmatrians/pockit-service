package com.bjb.pockit.repository;

import com.bjb.pockit.entity.PocketType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<PocketType, Long> {

}
