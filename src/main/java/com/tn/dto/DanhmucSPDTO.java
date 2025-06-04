package com.tn.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DanhmucSPDTO {
    private int id;
    private String tendanhmucSP;
    private String hinhanhDM;
    private String khuyenmai;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String tendanhmuc;
}
