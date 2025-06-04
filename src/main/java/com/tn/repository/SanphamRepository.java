package com.tn.repository;

import com.tn.entity.DanhmucSP;
import com.tn.entity.Sanpham;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SanphamRepository extends JpaRepository<Sanpham,Integer> {
    List<Sanpham> findByDanhmucSP_Id(Long danhmucSPId);

    // Tìm theo tên sản phẩm chứa từ khóa (không phân biệt hoa thường)
    List<Sanpham> findByTensanphamContainingIgnoreCase(String tensanpham);
}
