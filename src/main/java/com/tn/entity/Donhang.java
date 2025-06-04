package com.tn.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Table
@Entity
@Data
public class Donhang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;
    private String tensanpham;
    private long gia;


    @CreatedDate
    private LocalDateTime donhangDate;

    @PrePersist
    protected void onCreate() {
        this.donhangDate = LocalDateTime.now();
    }


    @ManyToOne
    @JoinColumn(name = "thongtin_id")
    private Thongtindonhang thongtindonhang;
}
