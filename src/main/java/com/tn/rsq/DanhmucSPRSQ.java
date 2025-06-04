package com.tn.rsq;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DanhmucSPRSQ {
    private String tendanhmucSP;
    private String khuyenmai;
//    private String hinhanhDM;
    private LocalDateTime createAt;
    private LocalDateTime updatedAt;
    private Integer danhmucId;
}
