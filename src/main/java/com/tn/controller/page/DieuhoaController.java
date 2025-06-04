package com.tn.controller.page;

import com.tn.dto.DanhmucSPDTO;
import com.tn.entity.DanhmucSP;
import com.tn.entity.Sanpham;
import com.tn.repository.DanhmucSPRepository;
import com.tn.service.DanhmucSPService;
import com.tn.service.SanphamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DieuhoaController {
    @Autowired
    private SanphamService sanphamService;

    @Autowired
    private DanhmucSPService danhmucSPService;

    @Autowired
    public DanhmucSPRepository danhmucSPRepo;

    @RequestMapping("/dienmay/dieuhoa")
    public String getAll(Model model){
        List<DanhmucSP> danhmucSPS = danhmucSPRepo.findAll();
        List<DanhmucSP> danhmucSPs = danhmucSPService.getProductsByCategoryId(24L);
        List<Sanpham> sanpham1s = sanphamService.getProductsByCategory(7L);



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



        model.addAttribute("danhmucSPDTOS", danhmucSPDTOS);
        model.addAttribute("getProductsByCategory", danhmucSPs);
        model.addAttribute("sanpham1s", sanpham1s);
        return "page/dienmay/dieuhoa";
    }
}
