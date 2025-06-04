package com.tn.rsq;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BannerRSQ {
    private String tenbanner;

    private LocalDateTime createAt;
    private LocalDateTime updatedAt;
}
