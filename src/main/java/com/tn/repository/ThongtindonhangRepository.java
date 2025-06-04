package com.tn.repository;

import com.tn.entity.Donhang;
import com.tn.entity.Thongtindonhang;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThongtindonhangRepository extends JpaRepository<Thongtindonhang, Integer> {
}
