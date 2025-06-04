package com.tn.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SanphamDTO {
    private int id;
    private String tensanpham;
    private String hinhanh;
    private String gia;
    private String thuonghieu;
    private String giamoi;
    private String sale;
    private Boolean hot;
    private String soluong;
    private LocalDateTime createdAt;

    private String tendanhmuc;


}
