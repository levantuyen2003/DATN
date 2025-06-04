package com.tn.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderItemDTO {
        private int id;
        private String tensanpham;
        private long gia;
        private LocalDateTime donhangDate;
        private String tennguoidat;
}
