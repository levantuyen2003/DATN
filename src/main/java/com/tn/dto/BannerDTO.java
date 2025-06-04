package com.tn.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BannerDTO {
    private int id;
    private String tenbanner;
    private String hinhanh;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
