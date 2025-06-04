package com.tn.controller.page;

import com.tn.dto.DanhmucSPDTO;
import com.tn.dto.SanphamDTO;
import com.tn.entity.Danhmuc;
import com.tn.entity.DanhmucSP;
import com.tn.entity.Sanpham;
import com.tn.repository.DanhmucRepository;
import com.tn.repository.DanhmucSPRepository;
import com.tn.repository.SanphamRepository;
import com.tn.service.DanhmucSPService;
import com.tn.service.SanphamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PageController {
    @Autowired
    public DanhmucSPRepository danhmucSPRepo;

    @Autowired
    public SanphamRepository sanphamRepo;

    @Autowired
    public DanhmucRepository danhmucRepo;

    @Autowired
    private DanhmucSPService danhmucSPService;

    @Autowired
    private SanphamService sanphamService;


    @RequestMapping("/page")
    public String getAll(Model model) {
        List<DanhmucSP> danhmucSPS = danhmucSPRepo.findAll();
        List<Danhmuc> danhmucs = (List<Danhmuc>) danhmucRepo.findAll();
        List<DanhmucSP> danhmucSPs = danhmucSPService.getProductsByCategoryId(24L);
        List<DanhmucSP> danhmucSPS1 = danhmucSPService.getProductsByCategoryId(25L);
        List<Sanpham> sanpham1s = sanphamService.getProductsByCategory(7L);
        List<Sanpham> sanpham2s = sanphamService.getProductsByCategory(12L);



        List<DanhmucSPDTO> danhmucSPDTOS = new ArrayList<>();
        danhmucSPS.forEach(obj -> {
            DanhmucSPDTO danhmucSPDTO = new DanhmucSPDTO();
            danhmucSPDTO.setId(obj.getId());
            danhmucSPDTO.setTendanhmucSP(obj.getTendanhmucSP());
            danhmucSPDTO.setHinhanhDM("../assets/img/imgDMSP/" + obj.getHinhanhDM());
            danhmucSPDTO.setKhuyenmai(obj.getKhuyenmai());
            danhmucSPDTO.setCreatedAt(obj.getCreatedAt());
            danhmucSPDTO.setUpdatedAt(obj.getUpdatedAt());

            if (obj.getDanhmuc() != null) {
                danhmucSPDTO.setTendanhmuc(obj.getDanhmuc().getTendanhmuc());
            }


            danhmucSPDTOS.add(danhmucSPDTO);
        });



        model.addAttribute("danhmucs", danhmucs);
        model.addAttribute("danhmucSPDTOS", danhmucSPDTOS);
        model.addAttribute("getProductsByCategory", danhmucSPs);
        model.addAttribute("getProductsByCategory2", danhmucSPS1);
        model.addAttribute("sanpham1s", sanpham1s);
        model.addAttribute("sanpham2s", sanpham2s);


        return "page/index";
    }
}
