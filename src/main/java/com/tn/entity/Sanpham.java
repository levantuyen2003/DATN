package com.tn.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Data
@Table
public class Sanpham {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;
    private String tensanpham;
    private String hinhanh;
    private String thuonghieu;
    private String giamoi;
    private String sale;
    private Boolean hot;
    private String gia;

    private String soluong;

    @CreatedDate
    private LocalDateTime createdAt;


    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @ManyToOne
    @JoinColumn(name = "danhmucSP_id")
    private DanhmucSP danhmucSP;
}
