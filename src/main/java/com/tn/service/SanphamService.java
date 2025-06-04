package com.tn.service;

import com.tn.entity.Sanpham;
import com.tn.repository.SanphamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SanphamService {
    @Autowired
    public SanphamRepository sanphamRepository;

    public List<Sanpham> getProductsByCategory(Long danhmucSPId){
        return sanphamRepository.findByDanhmucSP_Id(danhmucSPId);
    }

    public List<Sanpham> searchByTensanpham(String name) {
        if (!name.isEmpty()) {
            return sanphamRepository.findByTensanphamContainingIgnoreCase(name);
        } else {
            return sanphamRepository.findAll();
        }
    }

}
