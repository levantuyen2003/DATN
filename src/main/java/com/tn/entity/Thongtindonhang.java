package com.tn.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table
public class Thongtindonhang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;
    private String tennguoidat;
    private int sodienthoai;
    private String tinhtp;
    private String quanhuyen;
    private String xathon;
    private String diachi;
    private String thanhtoan;
    private long tongTien;

    @CreatedDate
    private LocalDateTime donhangDate;

    @PrePersist
    protected void onCreate() {
        this.donhangDate = LocalDateTime.now();
    }


    @OneToMany(mappedBy = "thongtindonhang", cascade = CascadeType.ALL)
    private List<Donhang> donhangs;
}
