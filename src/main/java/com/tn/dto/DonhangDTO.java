package com.tn.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class DonhangDTO {
    private int id;
    private String tennguoidat;
    private int sodienthoai;
    private String tinhtp;
    private String quanhuyen;
    private String xathon;
    private String diachi;
    private String thanhtoan;
    private LocalDateTime donhangDate;
    private long tongTien;

    private List<OrderItemDTO> items;
}

