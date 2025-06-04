package com.tn.repository;

import com.tn.entity.Danhmuc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface DanhmucRepository extends JpaRepository<Danhmuc, Integer> {
}
