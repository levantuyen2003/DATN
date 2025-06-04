package com.tn.repository;

import com.tn.entity.DanhmucSP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DanhmucSPRepository extends JpaRepository<DanhmucSP, Integer> {
    List<DanhmucSP> findByDanhmucId(Long danhmucId);
}
