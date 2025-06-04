package com.tn.service;

import com.tn.entity.DanhmucSP;
import com.tn.repository.DanhmucSPRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DanhmucSPService {
    @Autowired
    private DanhmucSPRepository danhmucSPRepo;

    public List<DanhmucSP> getProductsByCategoryId(Long danhmucId) {
        return danhmucSPRepo.findByDanhmucId(danhmucId);
    }
}
